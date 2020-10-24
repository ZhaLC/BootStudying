# 本篇涉及技术栈
动态代理设计模式  
AOP 自定义注解  
Spring-retry  
guava-retrying
# 参考资料
主要参考: https://blog.csdn.net/MarkerHub/article/details/106726694?utm_medium=distribute.pc_relevant.none-task-blog-title-4&spm=1001.2101.3001.4242
主要参考: https://www.cnblogs.com/mfrank/p/11336770.html#autoid-0-0-0
主要参考: https://www.jianshu.com/p/557eb67bb3d8
# 需求
接口异常需要重试  
需求的不断提出, 怎么迭代升级?  
更多接口需要加重试逻辑怎么处理?
# 需求导入(逐步完善)
## 基础接口异常重试
循环的方式, 可以实现简单重试的功能, 但是重试间隔等更多策略较麻烦
~~~
代码: com.zlc.studying.retry.simpleRetry.SimpleRetry
public class SimpleRetry {
    private final static int MAX_TIMES = 5;
    //调用处
    public void test(){
        int time = 0;
        while(time < MAX_TIMES){
            try {
                doSth();
            } catch (Exception e) {
                time++;
                if(time >= MAX_TIMES){
                    e.printStackTrace();
                }
            }
        }
    }
    // 实际工作的接口
    private void doSth(){
        System.out.println("doSth.................");
        throw new RuntimeException("测试重试");
    }

    public static void main(String[] args) {
        new SimpleRetry().test();
    }
}
~~~
## 加入动态代理
上述方式代码不好维护, 耦合性太高, 加入动态代理, 因为需要的地方肯定有没有实现接口的, 故采用cglib代理
~~~
代码: com.zlc.studying.retry.ProxyRetry.RetryProxyFactory

public class RetryProxyFactory implements MethodInterceptor {
    private Object target;
    public RetryProxyFactory(Object target) {
        this.target = target;
    }
    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        int times = 0;
        Object returnValue = null;
        while(times < 5){
            try {
                //参数
                returnValue = method.invoke(target, objects);
            } catch (Exception e) {
                times++;
                if(times >= 5){
                    e.printStackTrace();
                }
            }
         }
        return returnValue;
    }
}

public class ProxyRetry {
    public void doSth(){
        System.out.println("ProxyRetry doSth..........");
        throw new RuntimeException("测试ProxyRetry");
    }
    public static void main(String[] args) {
        ProxyRetry retry = new ProxyRetry();
        ProxyRetry proxyFactory = (ProxyRetry) new RetryProxyFactory(retry).getProxyInstance();
        proxyFactory.doSth();
    }
}
~~~
## AOP实现
策略不够灵活, 比如重试次数啥的, 当然也可以实现  
代码: com.zlc.studying.retry.aop.RetryAspect 不好使 //TODO  
参考: https://blog.csdn.net/MarkerHub/article/details/106726694?utm_medium=distribute.pc_relevant.none-task-blog-title-4&spm=1001.2101.3001.4242
# spring-retry
spring已经提供了比较完善的模块, 不需要自己再造轮子了  
~~~
依赖:
 <dependency>
    <groupId>org.springframework.retry</groupId>
    <artifactId>spring-retry</artifactId>
    <version>1.1.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.5.4</version>
</dependency>

~~~
## 注解式使用
### 优缺点
代码简洁方便  
@Recover 多个方法时无法指定方法
### 注解说明
@EnableRetry 需要开启注解  
@Retryable 标注此注解的方法在发生异常时会进行重试  
@Backoff 重试间隔策略  
@Recover 熔断机制 全部重试失败调用标有该注解的方法
### 代码实现
~~~
错误示例: 由于retry用到了aspect增强，所有会有aspect的坑，就是方法内部调用，会使aspect增强失效，那么retry当然也会失效
@RestController
public class Test {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("test")
    public void test() {
        logger.info("==============");
        try {
            process();
        } catch (Exception e) {
            logger.error("eeeeeeeeeeeeeeeee");
            e.printStackTrace();
        }
    }
    // TODO: 由于retry用到了aspect增强，所有会有aspect的坑，就是方法内部调用，会使aspect增强失效，那么retry当然也会失效
    @Retryable(value = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000L, multiplier = 1))
    public void process() {
        logger.info("test.........................");
        //这里写 1 / 0 下面的 @Recover 都不生效
        throw new RuntimeException("抛出异常");
    }
    @Recover
    public void recover(Exception e) {
        logger.info(" ---------------------------  ");
        logger.info(e.getMessage());
    }
}
正确示例:
@RestController
public class Test {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    Test2 test2;
    @RequestMapping("test")
    public void test() {
        logger.info("==============");
        try {
            test2.process();
        } catch (Exception e) {
            logger.error("eeeeeeeeeeeeeeeee");
            e.printStackTrace();
        }
    }
}
@Component
public class Test2 {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Retryable(value = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000L, multiplier = 1))
    public void process() {
        logger.info("test.........................");
        //这里写 1 / 0 下面的 @Recover 都不生效
        throw new RuntimeException("抛出异常");
    }
    @Recover
    public void recover(Exception e) {
        logger.info(" ---------------------------  ");
        logger.info(e.getMessage());
    }
}
~~~
## 方法式使用
### 优缺点
编码方式更加灵活, 而且可以在一个类里调用, 不会有注解式方法内部调用增强失效的问题
### 代码实现
~~~
RetryTemplate template = new RetryTemplate();
SimpleRetryPolicy policy = new SimpleRetryPolicy();
FixedBackOffPolicy policy1 = new FixedBackOffPolicy();
policy.setMaxAttempts(5);
policy1.setBackOffPeriod(1000);
//可以指定多个policy, 上面一个指定重试最大次数, 一个指定了时间间隔
template.setRetryPolicy(policy);
template.setBackOffPolicy(policy1);
boolean flag = template.execute(new RetryCallback<Boolean, Throwable>() {
    @Override
    public Boolean doWithRetry(RetryContext retryContext) throws Throwable {
        return null;
    }

});
还可以下面这样指定熔断方法:
Boolean execute = retryTemplate.execute(
        //RetryCallback
        retryContext -> {
            String hello = helloService.hello();
            log.info("调用的结果:{}", hello);
            return true;
        },
        // RecoverCallBack
        retryContext -> {
            //RecoveryCallback
            log.info("已达到最大重试次数");
            return false;
        }
    );
~~~
# guava retry
~~~
依赖:
 <dependency>
    <groupId>com.github.rholder</groupId>
    <artifactId>guava-retrying</artifactId>
    <version>2.0.0</version>
</dependency>
代码: 
public boolean googleRetry(String source, String dest) throws ExecutionException, RetryException {
    //定义重试机制
    Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            //重试条件
            .retryIfException()
            .retryIfRuntimeException()
            .retryIfExceptionOfType(Exception.class)
            .retryIfException(Predicates.equalTo(new Exception()))
            .retryIfResult(Predicates.equalTo(false))

            //等待策略 每次请求时间间隔
            .withWaitStrategy(WaitStrategies.fixedWait(myConfig.getRetrySleep(), TimeUnit.MILLISECONDS))

            //停止策略 : 尝试请求6次
            .withStopStrategy(StopStrategies.stopAfterAttempt(myConfig.getRetries()))

            //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
            //.withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))

            .build();

    //定义请求实现
    Callable<Boolean> callable = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            process(source, dest);
            return true;
        }
    };
    return retryer.call(callable);
}
~~~
# guava retry更多高级用法
参考: https://www.jianshu.com/p/557eb67bb3d8


