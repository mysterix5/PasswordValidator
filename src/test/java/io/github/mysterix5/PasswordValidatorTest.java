package io.github.mysterix5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * There is a password list as class variable with interesting cases. Every method in PasswordValidator is testet for every case.
 */
class PasswordValidatorTest {
    static final String[] ALL_TEST_PASSWORDS_ARR = {"", "asdf", "asdfasdf", "asdfasdfasdfasdfasdfasdfasdfasdf3", "asdfasd1", "asdfasdfasdfasdfasdfasdfasdfasd3",//next index 6
            "Asdf", "asdFasdf", "asdfasdfasdfasdfAsdfasdfasdfasdf3", "asdFasd1", "asdfasdfasdfasDfasdfasdfasdfasd3", "AAAAAAAAAAAAAAAAA5", "aaaaaaaaaaaaaaaaaa5", // next index 13
            "äsdFasd1", "a?=)(sdFasd1", "!@#&()–_[{}]:;',?/*~$^+=<>.", "!@#&()–_[{}]:;',?/*~$^+=<>.Aa1"};


    @Test // not very useful test, because it copies the logic of verify_password_arr() exactly.
    void verifyPasswordArr() {
        var pv = new PasswordValidator();
        boolean[] verArr = pv.verifyPasswordArr(ALL_TEST_PASSWORDS_ARR);
        for(int i = 0; i< ALL_TEST_PASSWORDS_ARR.length; i++){
            assertEquals(pv.verifyPassword(ALL_TEST_PASSWORDS_ARR[i]), verArr[i]);
        }
    }

    int [] verifyPasswordPassIndizes = {14,16};
    @Test
    void verifyPasswordPass() {
        var passwordInd = getPassOrFailIndexList(true, verifyPasswordPassIndizes);
        var pv = new PasswordValidator();
        for(int i: passwordInd){
            System.out.println("Password: " + ALL_TEST_PASSWORDS_ARR[i]);
            boolean actual = pv.verifyPassword(ALL_TEST_PASSWORDS_ARR[i]);
            assertTrue(actual);
        }
    }

    @Test
    void verifyPasswordFail(){
        var passwordInd = getPassOrFailIndexList(false, verifyPasswordPassIndizes);
        var pv = new PasswordValidator();
        for(int i: passwordInd){
            System.out.println("Password: " + ALL_TEST_PASSWORDS_ARR[i]);
            boolean actual = pv.verifyPassword(ALL_TEST_PASSWORDS_ARR[i]);
            assertFalse(actual);
        }
    }

    /** create a sub list from an index list referring to class variable all_test_passwords_arr
     *
     * @param getPass (insert true to get pass list, false to get fail list)
     * @param indexArrIn (list of indizes where the belonging passwords should be passing)
     */
    ArrayList<Integer> getPassOrFailIndexList(boolean getPass, int[] indexArrIn){
        var passList = IntStream.of(indexArrIn).boxed().collect(Collectors.toCollection(ArrayList::new));
        var failList = getOtherIndizes(passList);

        if(getPass) return passList;
        else return failList;
    }
    ArrayList<Integer> getOtherIndizes(ArrayList<Integer> intArr){
        ArrayList<Integer> allIndizes = new ArrayList<>(ALL_TEST_PASSWORDS_ARR.length);
        for(int i = 0; i < ALL_TEST_PASSWORDS_ARR.length; i++){
            allIndizes.add(i);
        }

        allIndizes.removeAll(intArr);
        return allIndizes;
    }


//    int [] check_length_pass_indizes = {2,4,5,7,9,10,11,12};
//    @Test
//    void check_length_pass() {
//        var password_ind = get_pass_or_fail_index_list(true, check_length_pass_indizes);
//        var pv = new PasswordValidator();
//        for(int i: password_ind){
//            System.out.println("Password: " + all_test_passwords_arr[i]);
//            boolean actual = pv.checkLength(all_test_passwords_arr[i]);
//            assertTrue(actual);
//        }
//    }
//
//    @Test
//    void check_length_fail() {
//        var password_ind = get_pass_or_fail_index_list(false, check_length_pass_indizes);
//        var pv = new PasswordValidator();
//        for(int i: password_ind){
//            System.out.println("Password: " + all_test_passwords_arr[i]);
//            boolean actual = pv.checkLength(all_test_passwords_arr[i]);
//            assertFalse(actual);
//        }
//    }
//
//
//    int [] check_contains_numbers_pass_indizes = {3,4,5,8,9,10,11,12};
//    @Test
//    void check_contains_numbers_pass() {
//        var password_ind = get_pass_or_fail_index_list(true, check_contains_numbers_pass_indizes);
//        var pv = new PasswordValidator();
//        for(int i: password_ind){
//            System.out.println("Password: " + all_test_passwords_arr[i]);
//            boolean actual = pv.checkContainsNumbers(all_test_passwords_arr[i]);
//            assertTrue(actual);
//        }
//    }
//    @Test
//    void check_contains_numbers_fail() {
//        var password_ind = get_pass_or_fail_index_list(false, check_contains_numbers_pass_indizes);
//        var pv = new PasswordValidator();
//        for(int i: password_ind){
//            System.out.println("Password: " + all_test_passwords_arr[i]);
//            boolean actual = pv.checkContainsNumbers(all_test_passwords_arr[i]);
//            assertFalse(actual);
//        }
//    }
//
//
//    int [] check_contains_upper_and_lower_case_pass = {6,7,8,9,10};
//    @Test
//    void check_contains_upper_and_lower_case_pass() {
//        var password_ind = get_pass_or_fail_index_list(true, check_contains_upper_and_lower_case_pass);
//        var pv = new PasswordValidator();
//        for (int i : password_ind) {
//            System.out.println("Password: " + all_test_passwords_arr[i]);
//            boolean actual = pv.checkContainsUpperAndLowerCase(all_test_passwords_arr[i]);
//            assertTrue(actual);
//        }
//    }
//    @Test
//    void check_contains_upper_and_lower_case_fail() {
//        var password_ind = get_pass_or_fail_index_list(false, check_contains_upper_and_lower_case_pass);
//        var pv = new PasswordValidator();
//        for (int i : password_ind) {
//            System.out.println("Password: " + all_test_passwords_arr[i]);
//            boolean actual = pv.checkContainsUpperAndLowerCase(all_test_passwords_arr[i]);
//            assertFalse(actual);
//        }
//    }


}