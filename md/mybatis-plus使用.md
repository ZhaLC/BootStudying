[toc]

# 官网

https://baomidou.com/

# 特性

* **无侵入:** 只做增强不做改变
* **性能损耗小:** 启动后自动注入基本crud, 性能基本无损耗, 直接面向对象操作
* **强大CRUD操作:** 内置通用mapper、通用service, 少量配置即可实现单表crud操作
* **支持lambda形式调用**
* **支持主键自动生成**
* **支持 ActiveRecord 模式**
* **支持自定义全局通用操作**
* **内置代码生成器:**  采用代码或maven插件可快速生成Mapper、Model、Service、Controller层代码, 支持模板引擎
* **内置分页插件:**  基于mybatis物理分页, 配置好插件后, 写分页等同于普通查询
* **分页插件支持多种数据库:**  MySQL、Oracle等
* **内置性能分析插件:**  可输出sql语句及执行时间
* **内置全局拦截插件:**  提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

# 视频教程(免费)

- [MyBatis-Plus 入门 - 视频教程 - 慕课网](https://www.imooc.com/learn/1130)

- [MyBatis-Plus 进阶 - 视频教程 - 慕课网](https://www.imooc.com/learn/1171)

# 快速开始

## Spring boot项目 

### 1. 引入依赖

~~~xml
<!--mp 3.4-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.1</version>
</dependency>

<!--druid连接池-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>

<!--mysql驱动-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.44</version>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
~~~

### 2. 配置文件

~~~yaml
# 使用的是druid连接池
spring:
  datasource:
    #type: com.alibaba.druid.pool.DruidDataSource
    druid:
      url: jdbc:mysql://localhost:3306/mqapi?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
      username: root
      password: 123456
      driver-class-name: com.mysql.jdbc.Driver
~~~

### 3.启动类添加@MapperScan注解, 扫描mapper类

~~~java
@MapperScan("com.xxx.fereport.mapper")
@SpringBootApplication
public class FeReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeReportApplication.class, args);
    }
}
~~~

### 4.实体类

~~~java
/**
 * @desc : 省略了一些属性
 **/
// @Data是lombok注解, 省去写get、set方法的麻烦
@Data
public class ProfitSummary impl {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属区域
     */
    private String region;

    /**
     * 逻辑删除, 1: 逻辑删除
     */
//    @TableLogic
    @TableField(select = false)
    private Integer deleted;

    /**
     * 导入操作人员id
     */
    @TableField(fill = FieldFill.INSERT)
    private String createId;

    /**
     * 更新(即逻辑删除)操作人员id 逻辑删除时自动注入不成功, 没有要求加这个就不要了
     */
//    @TableField(fill = FieldFill.UPDATE)
//    private String updateId;

    /**
     * 预留1
     */
    @TableField(select = false)
    private BigDecimal column1;

    /**
     * 预留4
     */
    @TableField(select = false)
    private String column4;
}
~~~

### 5.mapper类

~~~java
/**
 * @create : 2020-11-19 21:04
 * @desc :
 **/
public interface ProfitSummaryMapper extends BaseMapper<ProfitSummary> {

}
~~~

### 6. 简单增删改查测试类

~~~java
@SpringBootTest
class ProfitSummaryMapperTest {

   @Autowired
    ProfitSummaryMapper mapper;

    @Test
    void  testInsert(){
        ProfitSummary ps = new ProfitSummary();
        ps.setRegion("哪里");
        mapper.insert(ps);
    }
    
    @Test
    void testRemove(){
        QueryWrapper<ProfitSummary> qw = new QueryWrapper<>();
        //第一个属性是true, 才会执行这个, 可以在这里写一些判断参数的条件
        qw.eq(true, "region", "11");
        mapper.delete(qw);
    }

    @Test
    void testUpdate(){
        ProfitSummary ps = new ProfitSummary();
        ps.setColumn1(new BigDecimal("12.334"));
        UpdateWrapper<ProfitSummary> qw = new UpdateWrapper<ProfitSummary>();
        qw.eq(false, "region", "11").eq("column4","wansh");
        mapper.update(ps, qw);
    }

	 @Test
    void testQuery(){
        QueryWrapper<ProfitSummary> qw = new QueryWrapper<>();
        qw.eq(true, "region", "11");
        mapper.selectList(qw);
    }
}
~~~

## 一些注解说明

### 1.启动类MapperScan

启动类上加入该注解, 扫描mapper层

~~~java
@MapperScan("com.xxx.xxx.mapper")
@SpringBootApplication
public class FeReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeReportApplication.class, args);
    }
}
~~~

### 2. 实体Bean中

~~~java
@Data // @Data是lombok注解, 省去写get、set方法的麻烦
public class ProfitSummary {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
//    @TableLogic  //逻辑删除注解, 上例没有写, 见下一篇
    @TableField(select = false) //查询不查询该字段
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT) //插入时自动填充属性值, 上例没有写, 见下一篇
    private String createId;

//    @TableField(fill = FieldFill.UPDATE) //更新时自动填充属性值, 上例没有写, 见下一篇
//    private String updateId;
}
~~~

# 总结

上面是最简单的入门使用, 官网介绍都比较清楚; 在实际使用中使用更多的是service层crud接口。更多高级功能如自动生成代码、逻辑删除、自动填充属性、动态表名等见下一篇。



# 开篇

上一篇记录了mybatis-plus入门简单使用, 这一篇记录mybatis-plus代码生成器、动态表名、逻辑删除、自动填充、物理分页等高级功能在实际项目中的使用。初始依赖下面没有写, 在第一篇的基础上进行。(下述配置大部分都是根据官网来的, 依赖版本也是官网copy的, 但是可能由于官网未更新, 有一些过时的类, 但是不影响使用)。

# 代码生成器

代码生成器可以生成controller层、service层、mapper层代码

## 1、依赖

~~~xml
 <!--mp代码生成器-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.4.1</version>
</dependency>
<!--默认模板引擎依赖-->
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
    <version>2.2</version>
</dependency>
~~~

## 2、配置

运行下面main函数, 即可自动生成代码

~~~java
/**
 * @desc : mp代码生成器
 **/
public class MysqlGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zlc");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService"); //去掉Service接口的首字母I
        gc.setIdType(IdType.ID_WORKER_STR); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(false);//开启Swagger2模式
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.130.41.26:3306/fe?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("ucds");
        dsc.setPassword("ucds");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        // 这里设置模块名的话会在下面 pc.setParent("com.xxx.xxx"); 路径下新建一个模块,  没有使用
        pc.setModuleName(null); // 模块名
        pc.setParent("com.xxx.xxx");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 策略配置
        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(scanner("输入表名，多个英文逗号分割").split(","));
        //strategy.setInclude("income_summary");//对那一张表生成代码
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
~~~

## 3、生成的代码

**实体类**

~~~java
@Data
@EqualsAndHashCode(callSuper = false)
public class IncomeSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账期
     */
    private String accountPeriod;

    ...省略了其他属性
}
~~~

**controller层**

~~~java
@RestController
@RequestMapping("/income-summary")
public class IncomeSummaryController extends BaseController{
    
}
~~~

**service层**

~~~java
//接口
public interface IncomeSummaryService extends IService<IncomeSummary> {
    
}
//实现类
@Service
public class IncomeSummaryServiceImpl extends ServiceImpl<IncomeSummaryMapper, IncomeSummary> 
    implements IncomeSummaryService {
    
}
~~~

**mapper层**

~~~java
//接口
public interface IncomeSummaryMapper extends BaseMapper<IncomeSummary> {

}
~~~

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.xxx.xxx.mapper.IncomeSummaryMapper">

</mapper>
~~~

# 物理分页

## 逻辑分页

逻辑分页是指数据库返回全量数据, 由程序员在代码中进行分页。常用方法是一次性在数据库中获取所有数据, 存储到list中, 由于list有序, 再根据索引获取指定范围的数据。

## 物理分页

物理分页是指根据物理实体进行分页, 也就是MySQL服务器, 通过使用limit等关键字, 每次只查询符合要求的每页数据。

## 对比

1. **数据库负担:** 物理分页每次都访问数据库, 压力大。
2. **服务器负担:** 逻辑分页需要缓存全部数据, 占用内存较大。
3. **实时性:** 逻辑分页一次性读取数据, 数据库的最新状态不能实时展现; 物理分页每次都访问, 可以获取最新数据。

## 适用场景

根据上述对比, 可见逻辑分页更适用数据量不大、数据稳定的场景;  
物理分页适用于数据量比较大、更新频繁的场景。

```
QueryWrapper<ProfitSummary> qw = new QueryWrapper<>();
//qw.ge(StringUtils.isNotBlank(vo.getAccountPeriod()), "account_period", vo.getAccountPeriod().split(",")[0]);
//上面这行代码传""会报数组越界异常, 但是看源码是先执行的前面判断...
if(StringUtils.isNotBlank(vo.getAccountPeriod())){
    qw.ge("account_period", vo.getAccountPeriod().split(",")[0]);
    qw.le("account_period", vo.getAccountPeriod().split(",")[1]);
}
qw.eq(StringUtils.isNotBlank(vo.getSiteName()), "site_name", vo.getSiteName());
```

## mybatis-plus物理分页插件

mp物理分页不需要引入额外的依赖, 添加如下配置类即可:

~~~java
@Configuration
public class MybatisPlusConfig {

    /**
     * 动态表名  分页
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /**
         * 分页插件
         * 一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
         */
        // TODO z !-- 对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型: H2  -->
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}

~~~

## 示例代码

~~~java
@Service
public class IncomeSummaryServiceImpl extends ServiceImpl<IncomeSummaryMapper, IncomeSummary> 
    implements IncomeSummaryService {

    private Logger logger = LoggerFactory.getLogger(IncomeSummaryServiceImpl.class);

    @Autowired
    IncomeSummaryMapper incomeSummaryMapper;

    @Override
    public AjaxResultPo queryIncomeSummary(IncomeSummaryVo vo) {
        try {
            //分页条件
            Page<IncomeSummary> page = new Page<IncomeSummary>(vo.getPage(), vo.getRows());
            //createQueryWrapper()方法是构建的查询条件
            QueryWrapper<IncomeSummary> qw = createQueryWrapper(vo);
            //查询方法
            Page<IncomeSummary> incomeSummaryPage = incomeSummaryMapper.selectPage(page, qw);
            //下面handleBeanListBigDecimalScale()方法是业务逻辑对数据进行的一些格式化, 无需关注
            return AjaxResultPo.success("查询成功", incomeSummaryPage.getTotal(),
                    DecimalUtil.handleBeanListBigDecimalScale(incomeSummaryPage.getRecords(), 2));
        } catch (Exception e) {
            logger.error("查询出现异常: ", e);
            return AjaxResultPo.failed("查询出现异常");
        }
    }
}
~~~

上述代码如果不加MybatisPlusConfig.java中的分页插件配置, 生成的sql中不会添加limit关键字, 也就是不会进行物理分页, 会返回所有数据。

# 动态表名

动态表名需求使用场景可能是根据年份分表, 比如income_summary_2019, income_summary_2020, 需要根据前端参数来确定表的情况。动态表名在上述工程上继续开发, 不需要引入额外的依赖。

## 配置

动态表名和分页插件在一个插件中, 在上述MybatisPlusConfig.java继续修改。

~~~java
@Configuration
public class MybatisPlusConfig {

    /**
     * 需要加年后缀的表名 其他规则的话添加新的myDynamicTableNameParser方法
     */
    private static final String[] TABLE_NEED_YEAR = new String[]{"profit_summary","income_summary","cost_summary"};

    /**
     * 动态表名  分页
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /**
         * 动态表名
         */
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        HashMap<String, TableNameHandler> map = new HashMap<>();
        for (int i = 0; i < TABLE_NEED_YEAR.length; i++) {
            map.put(TABLE_NEED_YEAR[i], myDynamicTableNameParser());
        }
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(map);
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

        /**
         * 分页插件
         * 一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
         */
        // TODO z !-- 对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型: H2  -->
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }


    @Bean
    public TableNameHandler myDynamicTableNameParser() {
        return (sql, tableName) -> {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String year = (String) attr.getRequest().getAttribute("tableSuffix");
            if(year != null && !"".equals(year)){
                return tableName + "_" + year;
            }else{
                return tableName;
            }
        };
    }
}
~~~

# 逻辑删除

逻辑删除是指不真正将数据库中的数据删除, 而是使用标志位来进行标记, 比如设置deleted字段, 0为正常数据, 删除将0改为1, 这杨带来的问题是其他操作也要在sql中加上deleted=0条件比较麻烦, mp对此进行了封装, 只需要进行简单的配置, delete操作即可变成update操作, 其他查询等操作也不需要显示的在条件中加deleted=0条件。

## 配置

只需要在application.yml中添加如下配置即可:

~~~yaml
# mp逻辑删除全局配置(不配置也行, 默认值就是这个)
mybatis-plus:
  global-config:
    db-config:
      # 没有删除的
      logic-not-delete-value: 0
      # 逻辑删除的
      logic-delete-value: 1
~~~

然后bean属性字段上添加注解: @TableLogic

~~~java
/**
 * 逻辑删除, 1: 逻辑删除
 */
@TableLogic
@TableField(select = false)
private Integer deleted;
~~~

此时执行delete会变成 **update ...set deleted = 1**  
执行查询等会在select ...后加上 **where deleted = 0**

# 自动填充

自动填充比如在增加数据时, 默认添加操作人员id等属性, 无需在每处使用的地方都手动添加, 可以统一处理;  
关于自动生成的create_time、update_time等字段, 阿里规范上要求由数据库函数自动处理, 可以这杨写:

~~~sql
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '导入时间',
`update_time` datetime DEFAULT CURRENT_TIMESTAMPON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间, 即逻辑删除时间',
~~~

## mp实现

添加配置类:

~~~java
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 有这个属性才自动填充
        if(metaObject.hasSetter("createId")){
            // 这个属性代码中没有设置值的时候才自动填充
            Object var = getFieldValByName("createId", metaObject);
            if(var == null){
                //TODO z
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = requestAttributes.getRequest();
                SsoUserVo user = JwtUserUtil.getUserInfo(request);
                String userId = user.getId();
//                String userId = "createId001";
                setInsertFieldValByName("createId", userId, metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时自动填充 
        // TODO 说明: 上面说的逻辑删除虽然sql是update但是不会触发这里的自动填充
//        setUpdateFieldValByName("updateId", "up002",metaObject);
    }
}
~~~

同时需要在需要自动填充的字段上添加注解:  
**@TableField(fill = FieldFill.INSERT)**  插入数据自动填充  
**@TableField(fill = FieldFill.UPDATE)** 更新数据自动填充(**逻辑删除对此不生效**)

~~~java
 /**
     * 导入操作人员id
     */
    @TableField(fill = FieldFill.INSERT)
    private String createId;

    /**
     * 更新(即逻辑删除)操作人员id 逻辑删除时自动注入不成功, 没有要求加这个就不要了
     */
//    @TableField(fill = FieldFill.UPDATE)
//    private String updateId;
~~~

[toc]

### 引入依赖

~~~xml
<!--基于springboot的快速集成多数据源的启动器-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
    <version>3.1.0</version>
</dependency>

<!--druid连接池-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>
~~~

yml配置

~~~yaml
spring:
  autoconfigure:
    # 排除掉druid的原生配置
    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
  datasource:
    dynamic:
      # 以下是全局默认值，可以全局更改(所有数据源, 可以针对数据源做单独设置)
      druid:
        # 监控统计拦截的filters
        filters: stat
        # 配置初始化大小/最小/最大
        initial-size: 1
        min-idle: 1
        max-active: 20
        # 获取连接等待超时时间
        max-wait: 60000
        # 间隔多久进行一次检测，检测需要关闭的空闲连接
        time-between-eviction-runs-millis: 60000
        # 一个连接在池中最小生存的时间
        min-evictable-idle-time-millis: 300000
        validation-query: SELECT 'x'
        test-while-idle: true
        test-on-borrow: false
        test-on-return: false
        # 打开PSCache，并指定每个连接上PSCache的大小。oracle设为true，mysql设为false。分库分表较多推荐设置为false
        pool-prepared-statements: false
        max-pool-prepared-statement-per-connection-size: 20
        stat:
          merge-sql: true
          log-slow-sql: true
          slow-sql-millis: 2000
      # 设置默认的数据源或者数据源组,默认值即为master
      primary: master
      # 设置严格模式,默认false不启动. 启动后在未匹配到指定数据源时候回抛出异常,不启动会使用默认数据源
      strict: false
      datasource:
        # 智慧营销数据源
        master:
          url: jdbc:mysql://localhost:3306/bicReport?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
          username: ucds
          password: ucds
          driver-class-name: com.mysql.jdbc.Driver
        # 计费系统数据源
        cbs:
          url: jdbc:mysql://localhost:3306/cbs?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
          username: root
          password: MYSQL-ucloud-2020
          driver-class-name: com.mysql.jdbc.Driver
        # 内控系统数据源
        ics:
          url: jdbc:mysql://localhost:3306/ics?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
          username: root
          password: MYSQL-ucloud-2020
          driver-class-name: com.mysql.jdbc.Driver
          ########下面可以针对某个数据源进行个性化设置#############
          #druid: # 以下参数针对每个库可以重新设置druid参数
            #initial-size:
            #validation-query: select 1 FROM DUAL #比如oracle就需要重新设置这个
            #public-key: #（非全局参数）设置即表示启用加密,底层会自动帮你配置相关的连接参数和filter。
~~~

### 使用

service 层添加数据源名称

也可以在mapper层添加数据源名称, 注解一样

~~~java
@Service
@DS("master")
public class CostSummaryServiceImpl extends ServiceImpl<CostSummaryMapper, CostSummary> 
    implements CostSummaryService {
}
~~~

### 注意

1. 不添加使用默认数据源(primary), 上面配置中严格内模式strict: true时好像不添加也是会执行默认数据源;

2. 嵌套事务不会生效, 且在使用事务的service层方法中, 不会切换数据源,因为事务是在Connection实现, 而切换数据源会切换connection实现;详见:  https://blog.csdn.net/w57685321/article/details/106823660/ ;

3. serviceA(ics)  serviceB(不写数据源) serviceC(master):  A 中调用B和C, 两种情况: 

   加了事务: 全部使用数据源A;

   不加事务: A使用数据源ics, B使用数据源ics(注意并没有使用默认的master), C使用数据源master。

