package com.example.nuzlocke_tracker_api.global;

public class CustomExceptions {

    public static class TrainerNotFoundException extends RuntimeException {
        public TrainerNotFoundException(Integer id){
            super("No trainer found with id: " + id);
        }
    }

    public static class UserNotFoundExceptionString extends RuntimeException {
        public UserNotFoundExceptionString(String email){
            super("No trainer found with id: " + email);
        }
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Integer id){
            super("No user found with id: " + id);
        }
    }

    public static class RouteIdNotFoundException extends RuntimeException {
        public RouteIdNotFoundException(Integer id){
            super("No route found with id in the list: " + id);
        }
    }

    public static class NonValidPokemonException extends RuntimeException {
        public NonValidPokemonException(String name){
            super("No pokemon with name: " + name);
        }
    }

    public static class PokemonNotInPartyException extends RuntimeException {
        public PokemonNotInPartyException(String name){
            super(name + " Not in party or box");
        }
    }

}

