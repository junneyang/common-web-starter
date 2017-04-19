define({ "api": [
  {
    "group": "Test",
    "name": "test_name",
    "type": "get",
    "url": "/user/v1/test/?name={name}",
    "title": "test-desc",
    "version": "1.0.0",
    "permission": [
      {
        "name": "None"
      }
    ],
    "parameter": {
      "fields": {
        "Request-Params": [
          {
            "group": "Request-Params",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>field-desc</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "/user/v1/test/?name=xxx",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Response-Example(Success)",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": \"TEST\",\n\"errors\": null,\n\"timestamp\": 1491805791497,\n\"app\": \"myproject-demoservice\"\n}",
          "type": "json"
        },
        {
          "title": "Success-Code",
          "content": "| code  | msg\t\n| 2000  | SUCCESS",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Response-Example(Error)",
          "content": "{\n\"code\": -1,\n\"msg\": \"some error\",\n\"data\": null,\n\"errors\": null,\n\"timestamp\": 1491805791497,\n\"app\": \"myproject-demoservice\"\n}",
          "type": "json"
        },
        {
          "title": "Error-Code",
          "content": "| code  | msg\t\n| -1    | SOME_ERROR\n| 3000  | 权限异常, 认证授权失败\n| 4000  | 参数异常, 用户参数不合法\n| 5000  | 业务异常, 业务约束条件不满足",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/xxx/myproject/demoservice/user/controller/UserController.java",
    "groupTitle": "Test"
  }
] });
