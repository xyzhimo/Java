package tree;

public class BinarySearchTree {

    private static TreeNode root;

    public static void insert(int data) {
        TreeNode p = root;
        if (root == null) {
            root = new TreeNode(data);
            return;
        }
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new TreeNode(data);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new TreeNode(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public static TreeNode delete(int data) {
        TreeNode p = root; // 要删除的节点
        TreeNode pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        // 没有找到该节点
        if (p == null) {
            return null;
        }

        // 该节点有左右两个节点
        // 找右子数中最小的节点
        if (p.left != null && p.right != null) {
            TreeNode minPP = p;
            TreeNode minP = p.right;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            // 找到最小节点后, 替换 p 的数据
            p.data = minP.data;
            // 打算删除最小节点
            p = minP;
            pp = minPP;
        }

        // 经过上面两个节点的处理后, p 不可能再出现两个子节点
        // 要删除的 p 只有一个子节点或者没有子节点的情况
        TreeNode child = null;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // 开始真正的删除
        // 删除根节点, 且根节点只有一个子节点或者没有子节点
        // 根节点如果有两个节点的话, 不会走到这个 if 进来的
        if (pp == null) {
            root = child;
            // 如果要删除的 p 在 pp 的左边的话, pp 的左边 = child
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
        return root;
    }

    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public static int getHeight(TreeNode node) {
        if (node == null) return -1;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }


    public static void main(String[] args) {
        insert(5);
        insert(6);
        insert(4);
        insert(3);

//        preOrder(root);


//        inOrder(root);
//
//        delete(3);
//
//        inOrder(root);

        int height = getHeight(root);
        System.out.println(height);

    }

}
