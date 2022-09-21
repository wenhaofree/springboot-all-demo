package club.fuwenhao.service;

public interface ZkService {
    void addNode(String path);

    void checkPathExist(String path);

    void nodeChildren(String path);

    void dataChange(String path);

    void watchEvent(String path);

    void deleteNode(String path);
}
