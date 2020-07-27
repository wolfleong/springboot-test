package com.wl.swagger;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
//        webDataBinder.setValidator(validator);
    }

    /**
     * 查询单个用户
     */
    @ApiOperation("查询单个用户")
    @GetMapping("/{userId}")
    public ServiceResponse getUserById(@PathVariable @ApiParam("用户id") long userId) {
        return new ServiceResponse();
    }
    /**
     * 查询多个用户
     */
    @GetMapping()
    public ServiceResponse getUsers() {return null;}
    /**
     * 添加多个用户
     */
    @PostMapping("/addUsers")
    public ServiceResponse addUsers(@RequestBody List<User> users) {return null;}
    /**
     * 添加单个用户
     */
    @PostMapping("/user")
    public ServiceResponse addUser(@RequestBody @Validated({User.Add.class}) User user) {return null;}
    /**
     * 更新单个用户
     */
    @PutMapping("/user")
    public ServiceResponse updateUser(@RequestBody User user) {
        HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
        ValidatorFactory factory = configuration.failFast(true).buildValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> validate =
                validator.validate(user);
        return ServiceResponse.builder().code("0").message("fail").build();
    }
    /**
     * 删除单个用户
     */
    @DeleteMapping("/user")
    public ServiceResponse deleteUser(long userId) {return null;}
}

