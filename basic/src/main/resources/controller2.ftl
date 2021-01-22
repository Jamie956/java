
<#list entityMap?keys as Key>

    @Resource
    private ${Key}Service ${Key?uncap_first}Service;
</#list>

<#list entityMap?keys as Key>

    @ApiOperation("${entityMap[Key]}-分页查询")
    @ApiImplicitParam(name = "query", value = "查询封装对象", paramType = "body")
    @PostMapping("/${Key?uncap_first}")
    public Result<IPage<${Key}Model>> list${Key}(@RequestBody Query<${Key}Model> query) {
        IPage<${Key}Model> list = ${Key?uncap_first}Service.list(query);
        if (list == null) {
            return new Result<>(10001, "未找到${entityMap[Key]}", null);
        }
        return new Result<>(0, null, list);
    }

    @ApiOperation("${entityMap[Key]}-根据ID查询")
    @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/${Key?uncap_first}/{id}")
    public Result<${Key}Model> get${Key}(@PathVariable Long id) {
        ${Key}Model model = ${Key?uncap_first}Service.get(id);
        if (model == null) {
            return new Result<>(10001, "未找到${entityMap[Key]}", null);
        }
        return new Result<>(0, null, model);
    }
</#list>
