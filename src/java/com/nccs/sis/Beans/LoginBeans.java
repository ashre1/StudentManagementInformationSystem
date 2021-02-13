package com.nccs.sis.Beans;

import com.nccs.sis.DAO.LoginDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped

public class LoginBeans {

    private String UserName;

    private String Password;

    private String UserType;

    private String oddPass;

    private String newPass;

    private String confirmPass;

    Timer timer;

    private String flag = "false";

    private List<SelectItem> selectUser;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
        setId(this.UserName);
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public List<SelectItem> getcomboType() {
        selectUser = new ArrayList<SelectItem>();

        selectUser.add(new SelectItem("Admin", " Admin"));
        selectUser.add(new SelectItem("User", "User"));
        return selectUser;
    }

    public String getName() {
        String name = new LoginDAO().sendName(UserName);
        System.out.println(name);
        return name;
    }
    
    public String getImageId(){
        String imgId = new LoginDAO().sendId(UserName);
        System.out.println(imgId);
        return imgId;
        
    }

    public String getOddPass() {
        return oddPass;
    }

    public void setOddPass(String oddPass) {
        this.oddPass = oddPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean checkUser() {
        boolean user = new LoginDAO().user(UserName);
        if (user) {
            return false;
        } else {
            return true;
        }
    }

    public String login() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        time = params.get("time");
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;

        boolean loggedIn = false;

        loggedIn = new LoginDAO().checkLogin(UserName, Password, UserType);

        if (loggedIn) {
            if (checkUser()) {
                loggedIn = true;
                new LoginDAO().log(UserName);
                if (UserType.equalsIgnoreCase("admin")) {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", getName());
                    System.out.println(getName());
                    System.out.println("About to schedule task.");
                    timer = new Timer();
                    timer.schedule(new RemindTask(), 2 * 60 * 1000);
                    System.out.println("..................................");
                    return "adminPanel";
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", getName());
                    System.out.println("About to schedule task.");
                    timer = new Timer();
                    timer.schedule(new RemindTask(), 2 * 60 * 1000);
                    System.out.println("..................................");
                    return "userPanel";
                }
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error", "User Already Login");
                clear();
                loggedIn = false;
            }
        } else {
            if (UserName.isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "UserName is Missing");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error", "Invalid credentials");
                clear();
            }
            loggedIn = false;
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        return null;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean checkPassword() {
        boolean check = false;
        System.out.println("My in checkPassword");
        check = new LoginDAO().checkPass(UserName, oddPass);

        if (check) {
            return true;
        } else {
            return false;
        }

    }
    

    public String change() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = null;

        boolean ok = false;

        if (checkPassword()) {
            System.out.println("1st true");
            if (newPass.equals(confirmPass)) {
                System.out.println("2nd true");
                new LoginDAO().update(UserName, newPass);
                // fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successsfully Changed Password", ""));
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successsfully Changed Password", "");
                ok = true;
            } else {
                System.out.println("2nd false");
                //  fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Password didn't match", ""));
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password didn't match", "");
                ok = false;
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid credential", "");
            System.out.println("2nd false");
            // fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid credentials!!", ""));
            ok = false;
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("ok", ok);
        return null;
    }
    

    public String logout() {
       
       boolean yes = new LoginDAO().user(UserName);
       if(yes){
           new LoginDAO().logout(UserName);
       }else{
           new LoginDAO().logout2(UserName);
       }
       return "welcomePrimefaces";      
    }

    public void clear() {
        setPassword(null);
        setUserType(null);
    }

    class RemindTask extends TimerTask {

        public void run() {
            new LoginDAO().logout(UserName);
            System.out.println("time out");
            timer.cancel(); //Terminate the timer thread
        }
    }
    
    public String goString(){
        System.out.println("go go gone");
        return "adminPanel";
    }
}
