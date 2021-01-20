package DataStructure;

// 并查集
// 参考 https://blog.csdn.net/qq_42011541/article/details/83378709
public class UnionFind {
    // 存储这几伙强盗的逻辑关系，数组下标i代表第i伙强盗，值代表他老大是谁
    private int[] id;
    // 表示一共有几个强盗团伙
    private int count;
    // 保存团伙中有多少人
    private int[] sz;

    // 做初始化操作 N 代表一开始有几伙强盗
    public UnionFind(int N){
        count = N;
        id = new int[N];
        sz = new int[N];
        for(int i=0;i<N;++i){
            id[i] = i;
            sz[i] = i;
        }
    }
    // 获取强盗团伙的数量
    public int getCount(){
        return count;
    }
    // 判断p和q这两伙强盗是不是一家的
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    // 找到第p伙强盗的老大
    public int find(int p){
        if(p != id[p]){
            id[p] = find(id[p]);
        }
        return id[p];
    }
    // 联合两伙强盗，将p团伙全部合并到q所在的团伙中
    public void union(int p, int q){
        int pBoss = find(p);
        int qBoss = find(q);
        if(pBoss == qBoss){
            return;
        }
        if(sz[pBoss] < sz[qBoss]){
            id[pBoss] = qBoss;
            sz[qBoss] += sz[pBoss];
        }else{
            id[qBoss] = pBoss;
            sz[pBoss] += sz[qBoss];
        }
        --count;
    }
}
