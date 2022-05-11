package com.felix.shoppingcentre.soap.producer.controller;

import com.felix.shoppingcentre.entity.UserJpaEntity;
import com.felix.shoppingcentre.repository.UserRepository;
import com.felix.shoppingcentre.soap.producer.generated.Gender;
import com.felix.shoppingcentre.soap.producer.generated.User;
import com.felix.shoppingcentre.soap.producer.generated.UserRequest;
import com.felix.shoppingcentre.soap.producer.generated.UserResponse;
import com.felix.shoppingcentre.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @Endpoint Annotated classes will be registered with Spring WS for processing incoming SOAP messages.
 * @PayloadRoot Spring WS uses this annotation to find processing methods that match the namespace and localPart of the message.
 * @RequestPayload Identifies which parameter of the method the incoming message will be mapped to.
 * @ResponsePayload This annotation identifies how Spring WS maps method return values to response payloads.
 */
@Endpoint
public class UserWebServiceController {

    @Autowired
    private UserRepository userRepository;

    private static final String NAMESPACE_URI = "http://tutorial.spring.boot/soap/produce/user";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UserRequest")
    @ResponsePayload
    public UserResponse getUser(@RequestPayload UserRequest request) throws DatatypeConfigurationException {

        String name = request.getName();

        UserJpaEntity userJpa = userRepository.findByUsername(name);
        UserResponse response = new UserResponse();

        User user = new User();
        user.setName(name);

        if (!ObjectUtils.isEmpty(userJpa)
                && userJpa.getDelete() != ConstantUtils.USER_DELETED) {
            user.setEmail(userJpa.getEmail());
            user.setPhone(userJpa.getPhone());
            user.setCreatedTime(getXmlDate(userJpa.getCreatedTime()));
            user.setGender(userJpa.getGender() == ConstantUtils.FEMALE ?
                    Gender.FEMALE : userJpa.getGender() == ConstantUtils.MALE ? Gender.MALE :
                    Gender.UNKNOWN);
        }

        response.setUser(user);
        return response;
    }

    private XMLGregorianCalendar getXmlDate(Date createdTime) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(createdTime);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+5:00"));
        XMLGregorianCalendar gc = null;

        /**
         * Instant instant =new Date().toInstant();
         *            ZoneId zone = ZoneId.systemDefault();
         *            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
         *            LocalDate localDate= localDateTime.toLocalDate();
         */
        try {
            Instant instant =new Date().toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(String.valueOf(localDateTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }
}
