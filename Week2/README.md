# HashMap笔记

### 私有属性
```java
public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {
    // 序列号
    private static final long serialVersionUID = 362498820763181265L;    
    // 默认的初始容量是16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;   
    // 最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30; 
    // 默认的填充因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 当桶(bucket)上的结点数大于这个值时会转成红黑树
    static final int TREEIFY_THRESHOLD = 8; 
    // 当桶(bucket)上的结点数小于这个值时树转链表
    static final int UNTREEIFY_THRESHOLD = 6;
    // 桶中结构转化为红黑树对应的table的最小大小
    static final int MIN_TREEIFY_CAPACITY = 64;
    // 存储元素的数组，总是2的幂次倍
    transient Node<k,v>[] table; 
    // 存放具体元素的集
    transient Set<map.entry<k,v>> entrySet;
    // 存放元素的个数，注意这个不等于数组的长度。
    transient int size;
    // 每次扩容和更改map结构的计数器
    transient int modCount;   
    // 临界值 当实际大小(容量*填充因子)超过临界值时，会进行扩容
    int threshold;
    // 加载因子
    final float loadFactor;
}
```
##### loadFactor加载因子
loadFactor加载因子是控制数组存放数据的疏密程度，loadFactor越趋近于1，那么 数组中存放的数据(entry)也就越多，也就越密，也就是会让链表的长度增加，loadFactor越小，也就是趋近于0，数组中存放的数据(entry)也就越少，也就越稀疏。
    
**loadFactor太大导致查找元素效率低，太小导致数组的利用率低，存放的数据会很分散。loadFactor的默认值为0.75f是官方给出的一个比较好的临界值。**

给定的默认容量为 16，负载因子为 0.75。Map 在使用过程中不断的往里面存放数据，当数量达到了 16 * 0.75 = 12 就需要将当前 16 的容量进行扩容，而扩容这个过程涉及到 rehash、复制数据等操作，所以非常消耗性能。
##### capacity
Returns a power of two size for the given target capacity.
当HashMap初始化的时候会初始化为比给定capacity更大的一个2的n次幂
比如给定7，则初始化为8，给9则初始化为16

### hash
```java
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```
### put
1. 如果定位到的数组位置没有元素就直接插入。
2. 果定位到的数组位置有元素就和要插入的key比较，如果key相同就直接覆盖，如果key不相同，就判断p是否是一个树节点，如果是就调用putTreeVal将元素添加进入。如果不是就遍历链表插入(插入的是链表尾部)。
3. 如果插入链表后发现链表的长度大于TREEIFY_THRESHOLD会将链表转化为红黑树。
4. 如果putTreeVal发现容量小于MIN_TREEIFY_CAPACITY则会直接扩容而不是创建红黑树。
### get
1. 如果定位到的元素的key和hash值都相等，则直接返回
2. 如果定位到的元素是TreeNode,则在红黑树中查找，否则则遍历链表
### rehash
1. 进行扩容，会伴随着一次重新hash分配，并且会遍历hash表中所有的元素，是非常耗时的。在编写程序中，要尽量避免resize。
2. 每次扩容的容量是扩容前的二倍
### jdk1.7与jdk1.8区别
1.hash函数不同 jdk1.7会进行四次扰动，而jdk1.8仅进行了一次。扰动是为了防止写的不太好的hashCode函数带来过多的碰撞
2.jdk1.7是纯粹的拉链法解决冲突，而jdk1.8则在链表长度超过TREEIFY_THRESHOLD并且容量大于MIN_TREEIFY_CAPACITY时使用红黑树来代替链表，提升了冲突时的查询效率

# 心得体会
1. 递归不代表效率低，只要没有重复计算，递归是完全没有问题的，而且代码很整洁。




