package org.javarush.realproject.controller;

import org.javarush.realproject.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by APopov on 16.11.2015.
 */
@Component
public class UserValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return true;
        //return User.class.isAssignableFrom(aClass); //���� ������������ ������ - ��� User
        //������� �����, ��� , ���� ������ false, �� ����� �� ����� ��������������. �� ����� ����:
        //���� ��������� ����, � ���� ������ ���������, �� �� ������ ���������� �.�. supports = false ������� ������
        //��� ��� ��� �������� ������� ������������� ����������. �.�. ������, ��� supports ������ ���������� ������ false,
        //� ������-���� ������� (�� ����� �����������) - true, � ��� ����� �� - �� �����.
        //�������, ������� ����������� � ������ ( modelAndView.addObject(������) ) ����� ��������� ���������
        //� ������� , ��� ��� ��� ������ ���� ��������� ���������. � ����  �� ���� ��������� ���?
        //��� ������� - supports = true. � ��� � public void validate - ��� ����������� ��� ���� ��� (isInstance), � ���� �� ���� ��������� - ������ ������ �� ������
        // ....
        //��, ���. ...  ���� return true;, �� validate �� ������������� ����� ������. ����� ������ ��� ���������� @Valid ?
        // �� ����� ����� ���� ���-��� � �������� supports ?
        //� ���� ���������  ������ �� modelAndView.addObject("userPage", userPage);
        //� �� modelAndView.addObject("listOfUsers", userList); -  ���
        // ���� �������������
    }

    public void validate(Object o, Errors errors) {
        User user = (User)o;
        if (user.getName().length()<3){ //����������� ���-�� �������� - 3
            errors.reject("name_err", "1");
        }
        if (user.getName().length()>25){ //max ���-�� �������� - 25
            errors.reject("name_err", "2");
        }
        if (user.getAge()<5){ //��� �������
            errors.reject("age_err", "1");
        }
        if (user.getAge()>100){ //max �������
            errors.reject("age_err", "2");
        }
    }
}
