package com.neuronest.dto;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private int credits;

    public UserResponse() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UserResponse obj = new UserResponse();

        public Builder id(Long id) { obj.id = id; return this; }
        public Builder name(String v) { obj.name = v; return this; }
        public Builder email(String v) { obj.email = v; return this; }
        public Builder credits(int v) { obj.credits = v; return this; }
        public UserResponse build() { return obj; }
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getCredits() { return credits; }
}