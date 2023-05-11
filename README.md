# 1.login
## @PostMapping loginRe(id, pw)
### service.signIn(aop.SignControllerAspect) -> return String
#### if(id & !pw) = id_ok 
#### if(id & pw) = all_ok 
#### if(!id) = id_fail

# 2.join

## @PostMapping save(user, nickname) <- One To One Table
### service.signUp(aop.SignControllerAspect) -> not return
#### transaction will be add.... maybe