package io.github.mysterix5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    String[] all_test_passwords_arr = {"", "asdf", "asdfasdf", "asdfasdfasdfasdfasdfasdfasdfasdf3", "asdfasd1", "asdfasdfasdfasdfasdfasdfasdfasd3",//next index 6
            "Asdf", "asdFasdf", "asdfasdfasdfasdfAsdfasdfasdfasdf3", "asdFasd1", "asdfasdfasdfasDfasdfasdfasdfasd3", "AAAAAAAAAAAAAAAAA5", "aaaaaaaaaaaaaaaaaa5"};


    @Test // Ein bisschen sinnlos, weil die logik aus verify_password_arr() hier einfach nochmal wiederholt wird
    void verify_password_arr() {
        var pv = new PasswordValidator();
        boolean[] ver_arr = pv.verify_password_arr(all_test_passwords_arr);
        for(int i = 0; i< all_test_passwords_arr.length; i++){
            assertEquals(pv.verify_password(all_test_passwords_arr[i]), ver_arr[i]);
        }
    }

    int [] verify_password_pass_indizes = {9,10};
    @Test
    void verify_password_pass() {
        var password_ind = get_pass_or_fail_index_list(true, verify_password_pass_indizes);
        var pv = new PasswordValidator();
        for(int i: password_ind){
            System.out.println("Password: " + all_test_passwords_arr[i]);
            boolean actual = pv.verify_password(all_test_passwords_arr[i]);
            assertTrue(actual);
        }
    }

    @Test
    void verify_password_fail(){
        var password_ind = get_pass_or_fail_index_list(false, verify_password_pass_indizes);
        var pv = new PasswordValidator();
        for(int i: password_ind){
            System.out.println("Password: " + all_test_passwords_arr[i]);
            boolean actual = pv.verify_password(all_test_passwords_arr[i]);
            assertFalse(actual);
        }
    }


    int [] check_length_pass_indizes = {2,4,5,7,9,10,11,12};
    @Test
    void check_length_pass() {
        var password_ind = get_pass_or_fail_index_list(true, check_length_pass_indizes);
        var pv = new PasswordValidator();
        for(int i: password_ind){
            System.out.println("Password: " + all_test_passwords_arr[i]);
            boolean actual = pv.check_length(all_test_passwords_arr[i]);
            assertTrue(actual);
        }
    }

    @Test
    void check_length_fail() {
        var password_ind = get_pass_or_fail_index_list(false, check_length_pass_indizes);
        var pv = new PasswordValidator();
        for(int i: password_ind){
            System.out.println("Password: " + all_test_passwords_arr[i]);
            boolean actual = pv.check_length(all_test_passwords_arr[i]);
            assertFalse(actual);
        }
    }


    int [] check_contains_numbers_pass_indizes = {3,4,5,8,9,10,11,12};
    @Test
    void check_contains_numbers_pass() {
        var password_ind = get_pass_or_fail_index_list(true, check_contains_numbers_pass_indizes);
        var pv = new PasswordValidator();
        for(int i: password_ind){
            System.out.println("Password: " + all_test_passwords_arr[i]);
            boolean actual = pv.check_contains_numbers(all_test_passwords_arr[i]);
            assertTrue(actual);
        }
    }
    @Test
    void check_contains_numbers_fail() {
        var password_ind = get_pass_or_fail_index_list(false, check_contains_numbers_pass_indizes);
        var pv = new PasswordValidator();
        for(int i: password_ind){
            System.out.println("Password: " + all_test_passwords_arr[i]);
            boolean actual = pv.check_contains_numbers(all_test_passwords_arr[i]);
            assertFalse(actual);
        }
    }


    int [] check_contains_upper_and_lower_case_pass = {6,7,8,9,10};
    @Test
    void check_contains_upper_and_lower_case_pass() {
        var password_ind = get_pass_or_fail_index_list(true, check_contains_upper_and_lower_case_pass);
        var pv = new PasswordValidator();
        for (int i : password_ind) {
            System.out.println("Password: " + all_test_passwords_arr[i]);
            boolean actual = pv.check_contains_upper_and_lower_case(all_test_passwords_arr[i]);
            assertTrue(actual);
        }
    }
    @Test
    void check_contains_upper_and_lower_case_fail() {
        var password_ind = get_pass_or_fail_index_list(false, check_contains_upper_and_lower_case_pass);
        var pv = new PasswordValidator();
        for (int i : password_ind) {
            System.out.println("Password: " + all_test_passwords_arr[i]);
            boolean actual = pv.check_contains_upper_and_lower_case(all_test_passwords_arr[i]);
            assertFalse(actual);
        }
    }


    ArrayList<Integer> get_pass_or_fail_index_list(boolean get_pass, int[] index_arr_in){
        var pass_list = IntStream.of(index_arr_in).boxed().collect(Collectors.toCollection(ArrayList::new));
        var fail_list =get_other_indizes(pass_list);

        if(get_pass) return pass_list;
        else return fail_list;
    }
    ArrayList<Integer> get_other_indizes(ArrayList<Integer> int_arr){
        ArrayList<Integer> all_indizes = new ArrayList<>(all_test_passwords_arr.length);
        for(int i = 0; i < all_test_passwords_arr.length; i++){
            all_indizes.add(i);
        }

        all_indizes.removeAll(int_arr);
        return all_indizes;
    }

}