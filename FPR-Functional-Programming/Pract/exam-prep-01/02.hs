module Main where

{-
[[1, 2, 3],
[4, 5, 6],
[7, 8, 9]]

->
[[3, 6, 9],
[2, 5, 8],
[1, 4, 7]]
 -}

rotateMatrixLeft :: [[a]] -> [[a]]
rotateMatrixLeft [] = []
rotateMatrixLeft ([] : _) = []
rotateMatrixLeft mss = map last mss : rotateMatrixLeft (map init mss)

main :: IO ()
main = do
  print (rotateMatrixLeft [[1, 2, 3], [4, 5, 6], [7, 8, 9]])