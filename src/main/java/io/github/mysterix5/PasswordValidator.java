package io.github.mysterix5;

public class PasswordValidator {

    int min_length = 8;
    int max_length = 32;

    /** verify one single password
     *
     * @return boolean (acceptable or not)
     */
    public boolean verify_password(String password){
        System.out.println("verifying password: " + password);
        if(!check_length(password)) return false;
        if(!check_contains_numbers(password)) return false;
        if(!check_contains_upper_and_lower_case(password)) return false;

        System.out.println("password passed all checks!");
        return true;
    }

    /** verify array of passwords
     *
     * @return boolean array (acceptable for each password)
     */
    public boolean[] verify_password_arr(String[] password_arr){

        boolean[] verify_list = new boolean[password_arr.length];

        for (int i = 0; i< password_arr.length; i++){
            verify_list[i] = verify_password(password_arr[i]);
        }
        return verify_list;
    }

    /** check password length by instance variable min and max values
     *
     *
     */
    public boolean check_length(String password){
        if (password.length()<min_length){
            System.out.println("password is not long enough");
            return false;
        }
        if (password.length()>max_length){
            System.out.println("password is too long");
            return false;
        }

        return true;
    }

    /** checks if password contains number(s)
     */
    public boolean check_contains_numbers(String password){

        var char_arr = password.toCharArray();
        int[] number_arr = {0,1,2,3,4,5,6,7,8,9};
        for(char c: char_arr){
            if(Character.isDigit(c)) return true;
        }
        System.out.println("password has no digits");

        return false;
    }

    /** checks if password contains uppercase AND lowercase
     */
    public boolean check_contains_upper_and_lower_case(String password){
        boolean contains_upper = false;
        boolean contains_lower = false;

        for(char c: password.toCharArray()){
            if(Character.isLowerCase(c)) contains_lower = true;
            if(Character.isUpperCase(c)) contains_upper = true;
            if(contains_lower&&contains_upper)return true;
        }

        System.out.println("contains upper: " + contains_upper + ", lower: " + contains_lower);


        return false;
    }
}