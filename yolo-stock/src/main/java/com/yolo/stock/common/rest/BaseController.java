package com.yolo.stock.common.rest;

import com.aliyuncs.exceptions.ClientException;
import com.yolo.stock.common.biz.BaseBiz;
import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.common.msg.TableResultResponse;
import com.yolo.stock.common.util.Query;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-15 8:48
 */
@Slf4j
public class BaseController<Biz extends BaseBiz, Entity> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Biz baseBiz;


    @PostMapping(value = "")
    @ResponseBody
    @ApiOperation(value = "添加记录")
    public ObjectRestResponse<Entity> add(@RequestBody Entity entity) throws ClientException {
        baseBiz.insertSelective(entity);
        return new ObjectRestResponse<Entity>();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    @ApiOperation(value = "根据ID查询记录")
    public ObjectRestResponse<Entity> get(@PathVariable int id) {
        ObjectRestResponse<Entity> entityObjectRestResponse = new ObjectRestResponse<>();
        Object o = baseBiz.selectById(id);
        entityObjectRestResponse.data((Entity) o);
        return entityObjectRestResponse;
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ApiOperation(value = "更新记录")
    public ObjectRestResponse<Entity> update(@RequestBody Entity entity) {
        baseBiz.updateSelectiveById(entity);
        return new ObjectRestResponse<Entity>();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @ApiOperation(value = "根据ID删除记录")
    public ObjectRestResponse<Entity> remove(@PathVariable int id) {
        baseBiz.deleteById(id);
        return new ObjectRestResponse<Entity>();
    }

    @GetMapping(value = "/all")
    @ResponseBody
    @ApiOperation(value = "查询所有记录")
    public List<Entity> all() {
        return baseBiz.selectListAll();
    }

    @GetMapping(value = "/page")
    @ResponseBody
    @ApiOperation(value = "分页查询记录")
    public TableResultResponse<Entity> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByQuery(query);
    }

    public String getCurrentUserName() {
//        return BaseContextHandler.getUsername();
        return "YOLO";
    }
}
