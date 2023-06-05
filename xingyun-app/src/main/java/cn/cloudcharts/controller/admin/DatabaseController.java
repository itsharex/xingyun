package cn.cloudcharts.controller.admin;


import cn.cloudcharts.model.entity.Database;
import cn.cloudcharts.service.IDatabaseService;
import cn.cloudcharts.model.request.DataBaseRequest;
import cn.cloudcharts.core.domain.R;
import cn.cloudcharts.metadata.driver.DriverPool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * database management
 * </p>
 *
 * @author wuque
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    IDatabaseService databaseService;

    /**
     * 列表查询
     * @param dataBaseRequest
     * @return
     */
    @PostMapping(value ="/list")
    public R<PageInfo> list(@RequestBody DataBaseRequest dataBaseRequest){

        PageHelper.startPage(dataBaseRequest.getPageNum(), dataBaseRequest.getPageSize());
        List<Database> databaseList = databaseService.list();
        PageInfo<Database> info = new PageInfo<>(databaseList, dataBaseRequest.getPageSize());
        return R.ok(info);
    }

    /**
     * 添加数据源
     * @param database
     * @return
     */
    @PostMapping(value ="/add")
    public R add(@RequestBody Database database){

        return R.ok(databaseService.save(database));
    }

    @PutMapping
    public R<Void> saveOrUpdate(@RequestBody Database database) {
        if (databaseService.saveOrUpdateDataBase(database)) {
            DriverPool.remove(database.getName());
            return R.ok();
        } else {
            return R.fail("更新失败");
        }
    }

    @PostMapping("/getOneById")
    public R<Database> getOneById(@RequestParam(name = "id") String id) {
        Database database = databaseService.getById(id);
        return R.ok(database, "获取成功");
    }

    /**
     * 测试连通性
     * @param database
     * @return
     */
    @PostMapping("/testConnect")
    public R testConnect(@RequestBody Database database) {
        String msg = databaseService.testConnect(database);
        boolean isHealthy = "1".equals(msg);
        if (isHealthy) {
            return R.ok("数据源连接测试成功!");
        } else {
            return R.fail(msg);
        }
    }

}