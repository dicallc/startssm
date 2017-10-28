### 1.生产环境和开发环境通过配置文件的实现
### 2.简单的使用Spring注解
        @Controller处理http请求
        @RestController Spring 4之后新添加注解，返回json
        @RequestMapping配置url映射
        @Autowired 依赖注入
### 3.demo中使用到基本的接口访问(Controller)+访问数据库(dao)增删改查
### 4.使用@Valid 对接口输入参数进行验证
#### 用法:

1.对应属性添加验证
    <pre>
       @Min(value = 18,message = "未成年禁止入内")
        private Integer age;
    </pre>
    
2.对应Controller接口添加注解@Valid,同时追加参数BindingResult获得参数

<pre>
    @GetMapping(value = "/addgirl")
    public Girl addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        Girl girls = new Girl(girl.getCupSize(), girl.getAge());
        return girlDao.save(girls);
    }
</pre>

5.AOP
实例：使用@Aspect在接口请求之前，请求之后，返回内容都打印出来

6.巧用@ControllerAdvice 捕获异常返回内容
 