package com.shine.web.profile.dto;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class ShineUserDTO {
    private String name;

    private String family;

    private String userName;

    private String password;

    private String confirmPassword;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public static final class ShineUserDTOBuilder {
        private ShineUserDTO shineUserDTO;

        private ShineUserDTOBuilder() {
            shineUserDTO = new ShineUserDTO();
        }

        public static ShineUserDTOBuilder aShineUserDTO() {
            return new ShineUserDTOBuilder();
        }

        public ShineUserDTOBuilder withName(String name) {
            shineUserDTO.setName(name);
            return this;
        }

        public ShineUserDTOBuilder withFamily(String family) {
            shineUserDTO.setFamily(family);
            return this;
        }

        public ShineUserDTOBuilder withUserName(String userName) {
            shineUserDTO.setUserName(userName);
            return this;
        }

        public ShineUserDTOBuilder withPassword(String password) {
            shineUserDTO.setPassword(password);
            return this;
        }

        public ShineUserDTOBuilder withConfirmPassword(String confirmPassword) {
            shineUserDTO.setConfirmPassword(confirmPassword);
            return this;
        }

        public ShineUserDTO build() {
            return shineUserDTO;
        }
    }
}
