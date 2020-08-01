
# 找到配置文件
##修改仓库指向国内 *阿里* 仓库
配置文件如果没有指定 一般在 **conf/settings.xml** 中  
在配置文件中找到 **\<mirrors>\</mirrors>** 加入
```
<mirrors>
    <mirror>
      <id>alimaven</id>
      <mirrorOf>central</mirrorOf>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public</url>
    </mirror>
</mirrors>
```