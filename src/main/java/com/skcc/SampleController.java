package com.skcc;

import com.skcc.account.AccountRepository;
import com.skcc.com.SecurityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello, " + principal.getName());
        }

        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello " + principal.getName());
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboard();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());
        return "admin";
    }


//    Async 웹 MVC를 지원하는 필터: WebAsyncManagerIntegrationFilter
//    스프링 MVC의 Async 기능(핸들러에서 Callable을 리턴할 수 있는 기능)을 사용할 때에도 SecurityContext를 공유하도록 도와주는 필터.
//    PreProcess: SecurityContext를 설정한다.
//    Callable: 비록 다른 쓰레드지만 그 안에서는 동일한 SecurityContext를 참조할 수 있다.
//    PostProcess: SecurityContext를 정리(clean up)한다.
// 뭐 딱히 설정해줄건없고 이 필터가 서로 다른 스레드에 authentication를 공유하게 해준다는 거임.

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler(){
        SecurityLog.log("MVC");
        return new Callable<String>() {

            @Override
            public String call() throws Exception {
                SecurityLog.log("CALLABLE");
                return "async-handler";
            }
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService(){
        SecurityLog.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLog.log("MVC, after async service");

    return "Async-Service";
    }

}