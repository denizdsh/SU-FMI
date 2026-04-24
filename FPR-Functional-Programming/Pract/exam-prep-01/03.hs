module Main where

{-
[[1, 2, 3],
[4, 5, 6],
[7, 8, 9]]

->

[[1], [2], [3], [4], [5], [6], [7], [8], [9]]
 -}

spiral :: [[a]] -> [[a]]
spiral [] = []
spiral ([] : _) = []
spiral mss = separateMatrix $ rotateMatrixLeft mss

separateMatrix :: [[a]] -> [[a]]
separateMatrix [] = []
-- UNDER CONSTRUCTION
separateMatrix mss = [head $ head mss] : separateMatrix (tail $ head mss : tail mss)

rotateMatrixLeft :: [[a]] -> [[a]]
rotateMatrixLeft [] = []
rotateMatrixLeft ([] : _) = []
rotateMatrixLeft mss =
  map last mss : rotateMatrixLeft (map init mss)

main :: IO ()
main = do
  print (spiral [[1, 2, 3], [4, 5, 6], [7, 8, 9]])