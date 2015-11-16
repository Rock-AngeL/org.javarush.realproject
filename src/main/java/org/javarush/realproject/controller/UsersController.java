package org.javarush.realproject.controller;

/**
 * Created by APopov on 16.11.2015.
 */


import org.javarush.realproject.model.User;
import org.javarush.realproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Valk on 05.08.15.
 */

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.addValidators(new UserValidator());
        binder.registerCustomEditor(Timestamp.class,
                new PropertyEditorSupport() {
                    public void setAsText(String value) {
                        try {
                            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                            setValue(new Timestamp(parsedDate.getTime()));
                        } catch (ParseException e) {
                            setValue(null);
                        }
                    }
                });
        binder.registerCustomEditor(String.class,
                new PropertyEditorSupport() {
                    public void setAsText(String value) {
                        setValue(value);
                    }
                });
    }

    @RequestMapping("userList-DataTables")
    public ModelAndView userListDataTables() {
        List userList = userService.read();
        ModelAndView modelAndView = new ModelAndView("userList-DataTables");
        modelAndView.addObject("listOfUsers", userList);
        return modelAndView;
    }

    @RequestMapping("userList-myLocalTable")
    public ModelAndView userListmyLocalTable() {
        List userList = userService.read();
        ModelAndView modelAndView = new ModelAndView("userList-myLocalTable");
        modelAndView.addObject("listOfUsers", userList);
        return modelAndView;
    }

    @RequestMapping("userList-myTableAngular")
    public String userListmyTableAngular() {
        return "userList-myTableAngular";
    }

    @RequestMapping("users/page")
    @ResponseBody
    public UserPage users_page(@RequestParam(required = false) String pState) {
        try {
            pState = new String(pState.getBytes("ISO-8859-1"),"UTF8");
            //единственный вариант, который подошел. ’рень кака€-то. ¬ get-запросе, никак не получаетс€ установить кодировку
            //в заголовке - тупо игнорируетс€. “ела нет. ѕередать параметр можно только через параметры. „итал, что параметры
            // кодируютс€ utf-8, но ни фига подобного. »ли € чего-то не понимаю....
        } catch (UnsupportedEncodingException e) {}
        boolean needUpdate = false;
        PageStateInfo pageStateInfo = PageStateInfo.getInstance(pState);
        UserPage userPage = userService.readPage(pageStateInfo);
        if ((userPage.getPageList().size() == 0) && (userPage.getPagesCount() > 0)) { //Ѕƒ уже существенно изменилась
            pageStateInfo.setPageNumber(1);
            pageStateInfo.setPageOffset(0);
            userPage = userService.readPage(pageStateInfo);
            userPage.setNeedUpdate(true); //признак, что сост€ние пагинатора (индикаци€ текущей страницы) уже не актуально и надо его сбросить
            //так как возвращаем в модель фактически считанную страницу, то можно, конечно, обойтись без перемонной needUpdate,
            //а анализировать номер запрошенной и номер полученной страниц
        }
        return userPage;
    }

    @RequestMapping("users/delete")
    @ResponseBody
    public Integer users_page(@RequestParam(required = false) long id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "userList-myTable")
    public ModelAndView userListmyTable(@RequestParam(required = false) String pState) {
        boolean needUpdate = false;
        PageStateInfo pageStateInfo = PageStateInfo.getInstance(pState);
        UserPage userPage = userService.readPage(pageStateInfo);
        if ((userPage.getPageList().size() == 0) && (userPage.getPagesCount() > 0)) { //Ѕƒ уже существенно изменилась
            pageStateInfo.setPageNumber(1);
            pageStateInfo.setPageOffset(0);
            userPage = userService.readPage(pageStateInfo);
            needUpdate = true; //признак, что сост€ние пагинатора (индикаци€ текущей страницы) уже не актуально и надо его сбросить
            //так как возвращаем в модель фактически считанную страницу, то можно, конечно, обойтись без перемонной needUpdate,
            //а анализировать номер запрошенной и номер полученной страниц
        }
        ModelAndView modelAndView = new ModelAndView("userList-myTable");
        modelAndView.addObject("rowsPerPage", pageStateInfo.getRowsPerPage());
        modelAndView.addObject("pageNumber", pageStateInfo.getPageNumber());
        modelAndView.addObject("pageOffset", pageStateInfo.getPageOffset());
        modelAndView.addObject("orderField", pageStateInfo.getOrderField());
        modelAndView.addObject("orderFieldDesc", pageStateInfo.isOrderFieldDesc());
        modelAndView.addObject("userList", userPage.getPageList());
        modelAndView.addObject("pagesCount", userPage.getPagesCount());
        modelAndView.addObject("needUpdate", needUpdate);
        return modelAndView;
    }

    @RequestMapping(value = "userList-myTableQuery", method = RequestMethod.POST)
    public ModelAndView userListmyTable1(@RequestBody String pState) {
        ModelAndView modelAndView = userListmyTable(pState);
        return modelAndView;
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@RequestParam long id, @RequestBody(required = false) String pState) {
        userService.delete(id);
        return userListmyTable(pState);
    }

    @RequestMapping(value = "deletelocal", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestParam long id) {
        String result = "0";
        try {
            result = String.valueOf(userService.delete(id));
        } catch (Exception e) {
            return "-1";
        }
        return result;
    }

    @RequestMapping("createForm")
    public ModelAndView createForm(@RequestHeader("Referer") String back, @RequestParam(required = false) String source, @RequestParam(required = false) String name_err, @RequestParam(required = false) String age_err) {
        User user = new User();
        user.setName("");
        user.setAge(0);
        user.setIsAdmin(false);

        ModelAndView modelAndView = new ModelAndView("createForm");
        modelAndView.addObject("user", user);
        //modelAndView.addObject("source", source);
        modelAndView.addObject("source", back); //возвращаемс€ так
        modelAndView.addObject("name_err", name_err); //сообщени€ об ошибке
        modelAndView.addObject("age_err", age_err); //сообщени€ об ошибке
        return modelAndView;
    }

    @RequestMapping("updateForm")
    public ModelAndView updateForm(@RequestParam long id, @RequestParam String source, @RequestParam(required = false) String name_err, @RequestParam(required = false) String age_err) {
        //@RequestParam(required = false) String name, @RequestParam(required = false) String age
        //не об€зателные, т.к. используютс€ только при редиректе из update
        User user = userService.read(id);
        ModelAndView modelAndView = new ModelAndView("updateForm");
        modelAndView.addObject("user", user);
        modelAndView.addObject("source", source);
        modelAndView.addObject("name_err", name_err); //сообщени€ об ошибке
        modelAndView.addObject("age_err", age_err); //сообщени€ об ошибке
        return modelAndView;
    }

    @RequestMapping(value = "create")
    public ModelAndView createUser(@ModelAttribute @Valid User user, BindingResult bindingResult, @RequestParam boolean makeUpdate, @RequestParam String source) {
        ModelAndView modelAndView = null;
        if (makeUpdate) {
            if (bindingResult.hasErrors()) {
                modelAndView = new ModelAndView("redirect:createForm");
                modelAndView.addObject("source", source);
                for (ObjectError e : bindingResult.getAllErrors()) {
                    modelAndView.addObject(e.getCode(), e.getDefaultMessage());
                }
                return modelAndView;
            }
            try {
                userService.create(user);
                modelAndView = new ModelAndView("redirect:" + source);
            } catch (Exception e) {
                modelAndView = new ModelAndView("redirect:createForm?source=" + source);
                modelAndView.addObject("genError", "ќшибка при создании нового пользовател€ ! ");
            }
        } else {
            modelAndView = new ModelAndView("redirect:" + source);
        }
        return modelAndView;
    }

    @RequestMapping("update")
    public ModelAndView updateUser(@RequestParam long id, @ModelAttribute @Valid User user, BindingResult bindingResult, @RequestParam boolean makeUpdate, @RequestParam String source) {
        ModelAndView modelAndView = null;
        if (makeUpdate) {
            if (bindingResult.hasErrors()) {
                modelAndView = new ModelAndView("redirect:updateForm");
                //redirect потому, что иначе updateForm будет использована только дл€ отрисовки, но не в качестве адреса
                //
                //т.к. redirect, то addObject добавл€ет "объекты" не во вьюшку, а в адрес. ј дл€ адреса addObject - это добавление параметров URL
                modelAndView.addObject("id", id);
                modelAndView.addObject("source", source);
                for (ObjectError e : bindingResult.getAllErrors()) {
                    modelAndView.addObject(e.getCode(), e.getDefaultMessage());
                }
                return modelAndView;
            }
            try {
                userService.update(user);
                modelAndView = new ModelAndView("redirect:" + source);
            } catch (Exception e) {
                modelAndView = new ModelAndView("redirect:updateForm?id=" + id + "&source=" + source);
                modelAndView.addObject("genError", "ќшибка при сохранении пользовател€. ƒанные не изменены !");
            }
        } else {
            modelAndView = new ModelAndView("redirect:" + source);
        }
        return modelAndView;
    }

    /*
    HttpServletRequest request :
    The servlet container creates an HttpServletRequest object and passes it as an argument to the servlet's service methods (doGet, doPost, etc).
    */

}
