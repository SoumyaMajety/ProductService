package com.scaler.fakestoreapi.authcommon;
import com.scaler.fakestoreapi.dtos.UserDto;
import com.scaler.fakestoreapi.exceptions.InvalidTokenException;
import com.scaler.fakestoreapi.exceptions.ProductNotFound;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Data
@Component
public class AuthCommon {
    private RestTemplate restTemplate;

    public AuthCommon(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public  UserDto validateUser(String token) throws  InvalidTokenException {
          ResponseEntity<UserDto> userDto =
                  restTemplate.getForEntity("http://localhost:8081//users/validate/"+token,UserDto.class);

          if(!userDto.hasBody()){
              throw new InvalidTokenException("Invalid token"+userDto.getStatusCode() );
          }

        return userDto.getBody();
    }
}
