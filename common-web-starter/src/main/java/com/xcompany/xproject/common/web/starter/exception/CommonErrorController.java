package com.xcompany.xproject.common.web.starter.exception;
/*package com.xxx.myproject.demoservice.common.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xxx.myproject.demoservice.common.constant.ResponseCode;
import com.xxx.myproject.demoservice.common.response.APIResponse;

@Controller
@RequestMapping("/error")
@EnableConfigurationProperties({ServerProperties.class})
public class CommonErrorController implements ErrorController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonErrorController.class);
	private static final String ERROR_VIEW = "/error";
	
    private ErrorAttributes errorAttributes;
    @Autowired
	private APIResponse apiResponse;
	@Autowired
	private Environment environment;
    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    public CommonErrorController(ErrorAttributes errorAttributes) {
//        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

//    @RequestMapping(produces = "text/html",value = "404")
//    public ModelAndView errorHtml404(HttpServletRequest request,
//                                  HttpServletResponse response) {
//        response.setStatus(getStatus(request).value());
//        Map<String, Object> model = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        return new ModelAndView("error/404", model);
//    }
//
//
//    @RequestMapping(value = "404")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error404(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }
//
//
//    @RequestMapping(produces = "text/html",value = "500")
//    public ModelAndView errorHtml500(HttpServletRequest request,
//                                  HttpServletResponse response) {
//        response.setStatus(getStatus(request).value());
//        Map<String, Object> model = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        return new ModelAndView("error/500", model);
//    }
//
//    @RequestMapping(value = "500")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error500(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }

    @RequestMapping(value="", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)  // 200
    @ResponseBody
    public Object error(HttpServletRequest request) {
    	Map<String, Object> errorAttributes = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
    	
    	apiResponse.setStatus(Integer.parseInt(getStatus(request).toString()));
    	apiResponse.setCode(ResponseCode.UNKNOWN_EXCEPTION);
    	apiResponse.setMsg(errorAttributes.get("error").toString());
		apiResponse.setObjects(null);
		apiResponse.setData(null);

		List<Map<String, String>> errors = new ArrayList<Map<String, String>>();
		for (Map.Entry<String, Object> entry : errorAttributes.entrySet()) {
			LOGGER.debug("############################ key:"+  entry.getKey() + "\tvalue:" + entry.getValue());
			Map<String, String> error = new HashMap<String, String>();
			error.put("field", entry.getKey());
			error.put("message", entry.getValue().toString());
			errors.add(error);
		}
//		apiResponse.setErrors(errors);
		apiResponse.setErrors(null);
		return apiResponse.getEntry();
    }
    
    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes,
                includeStackTrace);
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public String getErrorPath() {
    	return ERROR_VIEW;
    }
}
*/