
package com.yuhao.web.common;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class LogService {
    private final Logger logger = LoggerFactory.getLogger(LogService.class);
    
  //日志来源工程名（用于ELK区别来源）
    @Value("${logFromProjectName}")
    private String logFromService;
    // 日志产品模式（00-测试模式，本打印所有日志 ； 01-产品模式，本地打印ERROR级日志；不管哪种模式，均输出日志至日志集群）
    @Value("${logging.product.mode}")
    private String logModel;
    //服务端口
    @Value("${server.port}")
    private String serverport;
  //日志集群通过下列listname从Redis取得 odn的日志
    @Value("${logstash.redis.list}")
    String logstashlistname;
    
    /**
     * 提醒日志
     *
     * @param o 日志对象
     */
    public void info(Object o) {
        accessLogger("INFO", o);
    }

    /**
     * 提醒日志
     *
     * @param o      日志对象
     * @param remark 备注
     */
    public void info(Object o, String remark) {
        accessLogger("INFO", o, remark);
    }

    /**
     * 错误日志
     *
     * @param o 日志对象
     */
    public void error(Object o) {
        accessLogger("ERROR", o);
    }

    /**
     * 错误日志
     *
     * @param o      日志对象
     * @param remark 备注
     */
    public void error(Object o, String remark) {
        accessLogger("ERROR", o, remark);
    }

    /**
     * 警告日志
     *
     * @param o 日志对象
     */
    public void warn(Object o) {
        accessLogger("WARN", o);
    }

    /**
     * 警告日志
     *
     * @param o      日志对象
     * @param remark 备注
     */
    public void warn(Object o, String remark) {
        accessLogger("WARN", o, remark);
    }

    /**
     * HTTP 访问日志
     *
     * @param o 日志对象
     */
    public void httpIn(Object o) {
        accessLogger("IN", o);
    }

    /**
     * HTTP 访问日志
     *
     * @param o      日志对象
     * @param remark 备注
     */
    public void httpIn(Object o, String remark) {
        accessLogger("IN", o, remark);
    }

    /**
     * HTTP 反馈日志
     */
    public void httpOut(Object o) {
        accessLogger("OUT", o);
    }

    /**
     * HTTP 反馈日志
     // *
     * @param o      日志对象
     * @param remark 备注
     */
    public void httpOut(Object o, String remark) {
        accessLogger("OUT", o, remark);
    }


    /**
     * 日志服务
     *
     * @param accessType 日志类型
     * @param o          对象
     */
    private void accessLogger(String accessType, Object o) {
        accessLogger(accessType, o, "");
    }

    /**
     * 异常业务日志
     *
     * @param accessType 日志类型
     * @param o          对象
     */

    /**
     * @param remark 备注信息
     * @Param reason  业务失败原因，反馈至前台
     * @Param checkpoint 检查点，定位异常发生的位置，一般格式 类名-方法名
     * @Param message  抛出的异常信息
     */
    public void businessExceptionLog(String reason, String checkpoint, String message, String remark) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("reason", reason);
        map.put("checkpoint", checkpoint);
        map.put("message", message);
        error(map, remark);
    }


    /**
     * 日志服务
     *
     * @param accessType 日志类型
     * @param o          对象
     * @param remark     备注
     */

    private void accessLogger(String accessType, Object o, String remark) {
        String logstr = JSON.toJSONString(o);
        JSONObject jobj = new JSONObject();

        jobj.put("content", logstr); //设置内容
        jobj.put("accepttime", new Date());
        jobj.put("ipaddress", getLocalIP());
        jobj.put("serverport", serverport); //服务端口
        if (StringUtils.isNotBlank(remark)) {
            jobj.put("addremark", remark);
        }
        logstr = JSON.toJSONString(jobj);
        if (StringUtils.isNotBlank(logstr)) {
            logstr = logstr.replace("\\", "");
        }

        try {
//            测试模式
            if (StringUtils.equalsIgnoreCase("00", logModel)) {
                //保存本地日志
                if ("INFO".equalsIgnoreCase(accessType) || "IN".equalsIgnoreCase(accessType) || "OUT".equalsIgnoreCase
                        (accessType)) {
                    logger.info(logstr);
                }
                if ("WARN".equalsIgnoreCase(accessType)) {
                    logger.warn(logstr);
                }
                //进入消息队列logList
//                rediscomponent.listLeftpush(logstashlistname, logstr);
            }
            if ("ERROR".equalsIgnoreCase(accessType)) {
                logger.error(logstr);
            }
        } catch (Exception e) {
            logger.error("LogFromService:" + logFromService + " 写日志出错:" + e.getMessage());
        }
    }

    /**
     * 获得本地IP地址
     *
     * @return IP
     */

    public String getLocalIP() {
        String ipaddress = "";
        try {
            InetAddress ip = null;
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                //System.out.println(netInterface.getName());
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
//                        System.out.println("本机的ip=" + ip.getHostAddress());
                        ipaddress = ip.getHostAddress();
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80") && !ipaddress.contains("127.0.0.1") && !ipaddress.contains("192.168.122.1")) {
                            break;
                        } else {
                            ipaddress = "";
                        }
                    }
                }
                if (StringUtils.isNotBlank(ipaddress)) {
                    break;
                }
            }
        } catch (Exception e) {
            ipaddress = "127.0.0.1";
        }
        return ipaddress;
    }
}

      
