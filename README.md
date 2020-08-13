# scaffold
Backstage management module  
物联网后台管理系统  
##实现功能
与设备进行通讯  
前端向后端发送加密数据  
使用mysql 作为 session存储器
##目录结构  
###java  
|命名空间| | | |类|说明|  
|:---|:---|---|---|---|:---:|
|com.liuhuiyu.scaffold| | | |App|启动|
| | | | |ServletInitializer|启动|
|  |annotation| | | |自定义注解|
|  |aspect| | | |拦截器|
|  |configuration| | | |配置文件|
|  |constant| | | |常量|
|  |controller| | | |网站映射|
|  |dao| | | |数据视图dao|
|  |domain| | | |数据模型定义|
|  | |entity| | |数据库模型|
|  | |model| | |本地模型|
|  | |view| | |数据库视图模型|
|  |exception| | | |自定义异常|
|  |factory| | | |工厂类|
|  |filter| | | |过滤器|
|  |repository| | | |数据库模型jpa接口|
|  |service| | | |服务|
|  |socket| | | |外部设备通讯|
|  |utils| | | |自定义工具|
###web
|文件夹| | | |说明|
|:---|:---|:---|:---|:---|
|static| | | |静态文件|
| |common| | |公共静态文件|
| | |jquery| |jquery|
| | |security| |通讯安全|
| | |vue| |vue|
| |file| | |静态文件|
| |system| | |本系统专用|
|templates| | | |模板|
