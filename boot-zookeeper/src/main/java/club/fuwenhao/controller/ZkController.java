package club.fuwenhao.controller;

import club.fuwenhao.service.ZkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZkController {

    @Autowired
    private ZkService zkService;

    @GetMapping("/addNode")
    public void addNode(@RequestParam("path") String path) {
        zkService.addNode(path);
//        zkService.checkPathExist(path);
//        zkService.nodeChildren(path);
//        zkService.deleteNode(path);
//        zkService.dataChange(path);
    }
}
