module Main where

{-
[[1, 2, 3],
[4, 5, 6]]

->

[[1, 4],
[2, 5],
[3, 6]]
 -}

transpose :: [[a]] -> [[a]]
transpose [] = []
transpose ([] : _) = []
transpose mss = map head mss : transpose (map tail mss)

main :: IO ()
main = do
  print (transpose [[1, 2, 3], [4, 5, 6]])