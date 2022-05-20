package io.github.mysterix5;

public class PasswordValidator {

    private int minLength = 8;
    private int maxLength = 32;

    private String specialCharacters = "!@#&()–_\\[{}\\]\\.:;',?/*~$^+=<>";

    /** verify one single password
     *
     * @return boolean (acceptable or not)
     */
    public boolean verifyPassword(String password){
        System.out.println("verifying password: " + password);
        if(!checkAllCharactersAllowed(password)) return false;
        if(!checkLength(password)) return false;
        if(!checkContainsNumbers(password)) return false;
        if(!checkContainsUpperAndLowerCase(password)) return false;

        System.out.println("password passed all checks!");
        return true;
    }

    /** verify array of passwords
     *
     * @return boolean array (acceptable for each password)
     */
    public boolean[] verifyPasswordArr(String[] passwordArr){

        boolean[] verifyList = new boolean[passwordArr.length];

        for (int i = 0; i< passwordArr.length; i++){
            verifyList[i] = verifyPassword(passwordArr[i]);
        }
        return verifyList;
    }

    /** check password length by instance variable min and max values
     *
     *
     */
    private boolean checkLength(String password){
        if (password.length()< minLength){
            System.out.println("password is not long enough");
            return false;
        }
        if (password.length()> maxLength){
            System.out.println("password is too long");
            return false;
        }

        return true;
    }

    /** checks if password contains number(s)
     */
    private boolean checkContainsNumbers(String password){

        for(char c: password.toCharArray()){
            if(Character.isDigit(c)) return true;
        }
        System.out.println("password has no digits");

        return false;
    }

    /** checks if password contains uppercase AND lowercase
     */
    private boolean checkContainsUpperAndLowerCase(String password){
        boolean containsUpper = false;
        boolean containsLower = false;

        for(char c: password.toCharArray()){
            if(Character.isLowerCase(c)) containsLower = true;
            if(Character.isUpperCase(c)) containsUpper = true;
            if(containsLower&&containsUpper)return true;
        }

        System.out.println("contains upper: " + containsUpper + ", lower: " + containsLower);


        return false;
    }

    private boolean checkAllCharactersAllowed(String password){

        if (password.matches(String.format("[a-zA-Z0-9%s]+", specialCharacters))) return true;

        System.out.println("there is a forbidden character in your password");

        return false;
    }
}