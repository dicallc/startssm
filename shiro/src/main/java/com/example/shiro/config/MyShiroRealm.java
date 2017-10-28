package com.example.shiro.config;

import com.example.shiro.domain.SysPermission;
import com.example.shiro.domain.SysRole;
import com.example.shiro.domain.UserInfo;
import com.example.shiro.service.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    private final static Logger logger= LoggerFactory.getLogger(MyShiroRealm.class);
    @Resource
    private UserInfoService userInfoService;

    /**
     * 登录及认证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        String username = (String) principals.getPrimaryPrincipal();
        //2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
        UserInfo userInfo = userInfoService.findByUsername(username);
        for (SysRole role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 授权
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo=" + userInfo);
        if (userInfo == null) {
            throw new AccountException("帐号或密码不正确！");
        }
        //得到加密密码的盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        logger.info("加密密码的盐：" + salt);
//        //得到盐值加密后的密码：只用于方便数据库测试，后期不会用到。
        logger.info("用户名: "+username+" 密码："+userInfo.getPassword());
        Object md = new SimpleHash("MD5",userInfo.getPassword(),salt,1024);
        logger.info("盐值加密后的密码："+md);
//        创建简单授权对象
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户名
                userInfo.getPassword(), //密码
                salt,//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterations = 1024;
        //salt YWRtaW4= 038bdaf98f2037b31f1e75b5b4c9b26e
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println("salt: "+salt+" result: "+result);
    }
}
