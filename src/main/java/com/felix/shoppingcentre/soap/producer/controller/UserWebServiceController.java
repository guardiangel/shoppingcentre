package com.felix.shoppingcentre.soap.producer.controller;

import com.felix.shoppingcentre.soap.producer.generated.Gender;
import com.felix.shoppingcentre.soap.producer.generated.User;
import com.felix.shoppingcentre.soap.producer.generated.UserRequest;
import com.felix.shoppingcentre.soap.producer.generated.UserResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;

/**
 * @Endpoint
 * Annotated classes will be registered with Spring WS for processing incoming SOAP messages.
 * @PayloadRoot
 * Spring WS uses this annotation to find processing methods that match the namespace and localPart of the message.
 * @RequestPayload
 * Identifies which parameter of the method the incoming message will be mapped to.
 * @ResponsePayload
 * This annotation identifies how Spring WS maps method return values to response payloads.
 */
@Endpoint
public class UserWebServiceController {
    private static final String NAMESPACE_URI = "http://tutorial.spring.boot/soap/produce/user";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UserRequest")
    @ResponsePayload
    public UserResponse getUser(@RequestPayload UserRequest request) throws DatatypeConfigurationException {
        UserResponse response = new UserResponse();
        User user = new User();
        String name = request.getName();
        user.setName(name);
        switch (name) {
            case "Mike":
                user.setBirth(
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(
                                LocalDate.of(2000, 1, 1).toString()
                        )
                );
                user.setGender(Gender.MALE);
                break;
            case "Ketty":
                user.setBirth(
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(
                                LocalDate.of(2010, 12, 31).toString()
                        )
                );
                user.setGender(Gender.FEMALE);
                break;
            default:
                user.setGender(Gender.UNKNOWN);
                break;
        }
        response.setUser(user);
        return response;
    }
}
