package cn.windylee;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Realm realm = new MyCustomRealm();
        SecurityManager securityManager = new DefaultSecurityManager(realm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("user", "password");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            }catch (UnknownAccountException uae){
                logger.error("Username Not Found! {}", uae);
            }catch (IncorrectCredentialsException ice){
                logger.error("Invalid Credentials! {}", ice);
            }catch (LockedAccountException lae){
                logger.error("Your Account is Locked! {}", lae);
            }catch (AuthenticationException ae){
                logger.error("Unexpected Error! {}", ae);
            }
        }

        logger.info("User [{}] logged in successfully", currentUser.getPrincipal());

        if(currentUser.hasRole("admin")){
            logger.info("Welcome Admin");
        }else if(currentUser.hasRole("editor")){
            logger.info("Welcome, Editor");
        }else if(currentUser.hasRole("author")){
            logger.info("Welcome, Author!");
        }else{
            logger.info("Welcome, Guest");
        }

        if(currentUser.isPermitted("articles:compose")){
            logger.info("You can compose an article");
        }else{
            logger.info("Your are not permitted to compose an article");
        }

        if(currentUser.isPermitted("articles:save")){
            logger.info("Your can save articles");
        }else{
            logger.info("Your can not save article");
        }

        if(currentUser.isPermitted("articles:publish")){
            logger.info("Your can publish articles");
        }else{
            logger.info("You can not publish articles");
        }

        Session session = currentUser.getSession();
        session.setAttribute("key", "value");
        String value = (String) session.getAttribute("key");
        if(value.equals("value")){
            logger.info("Retrieved the correct value! [{}]", value);
        }
        currentUser.logout();
    }

}
