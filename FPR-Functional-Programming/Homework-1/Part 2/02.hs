data BinTree = Nil | Node Int BinTree BinTree

-- deriving (Show, Eq)

areEqual :: BinTree -> BinTree -> Bool
-- if BinTree is derived from Eq:
-- areEqual = (==)
areEqual Nil Nil = True
areEqual Nil _ = False
areEqual _ Nil = False
areEqual (Node x l1 r1) (Node y l2 r2) = x == y && areEqual l1 l2 && areEqual r1 r2

mirrorTree :: BinTree -> BinTree
mirrorTree Nil = Nil
mirrorTree (Node x l r) = Node x (mirrorTree r) (mirrorTree l)

data Tree = NIl | NOde Int [Tree]

stringifyTreeBranches :: [Tree] -> String
stringifyTreeBranches [] = ""
stringifyTreeBranches [t] = stringifyTree t
stringifyTreeBranches (t : ts) = stringifyTree t ++ "," ++ stringifyTreeBranches ts

stringifyTree :: Tree -> String
-- just derive Tree from Show :(
stringifyTree NIl = []
stringifyTree (NOde x []) = "[" ++ show x ++ "]"
stringifyTree (NOde x branches) =
  "[" ++ show x ++ "," ++ "[" ++ stringifyTreeBranches branches ++ "]" ++ "]"

findRoot :: [(Int, Int)] -> Int
findRoot pairs =
  case filter (`notElem` [c | (_, c) <- pairs]) [p | (p, _) <- pairs] of
    (r : _) -> r
    [] -> error "No root found"

buildTreeFrom :: [(Int, Int)] -> Int -> Tree
buildTreeFrom pairs n = NOde n ([buildTreeFrom pairs c | (p, c) <- pairs, p == n])

buildTree :: [(Int, Int)] -> Tree
buildTree [] = NIl
buildTree pairs = buildTreeFrom pairs (findRoot pairs)

main :: IO ()
main =
  do
    let tree = Node 5 (Node 4 Nil Nil) (Node 3 (Node 1 Nil Nil) (Node 2 Nil Nil)) :: BinTree
    let tree2 = Node 5 (Node 4 (Node 8 Nil Nil) Nil) (Node 3 (Node 1 Nil Nil) (Node 2 Nil Nil)) :: BinTree
    let expectedResult = Node 5 (Node 3 (Node 2 Nil Nil) (Node 1 Nil Nil)) (Node 4 Nil Nil) :: BinTree

    print "Binary tree 1 equal to self:"
    print $ areEqual tree tree -- Очакван резултат: True
    print "Binary tree 1 equal to Binary tree 2:"
    print $ areEqual tree tree2 -- Очакван резултат: False
    print "Mirrors in check:"
    print $ areEqual expectedResult (mirrorTree tree) -- Очакван резултат: True
    let tree =
          NOde
            5
            [ NOde 6 [],
              NOde 7 [NOde 10 [], NOde 11 []],
              NOde 8 [NOde 9 []]
            ]

    print "Stringify rose tree:"
    print $ stringifyTree tree -- Очакван резултат: [5,[[6],[7,[[10],[11]]],[8,[[9]]]]]
    let builtTree = buildTree [(8, 9), (5, 3), (5, 4), (1, 5), (8, 2), (1, 8), (8, 6)]
    --           1
    --        /     \
    --       5       8
    --     / |     / | \
    --    3  4    9  2  6
    print "Built tree:"
    print $ stringifyTree builtTree -- Очакван резултат: [1,[[5,[[3],[4]]],[8,[[9],[2],[6]]]]]
