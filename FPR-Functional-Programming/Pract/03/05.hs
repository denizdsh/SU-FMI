module Main where

-- [1,2,3,4,5,6,7] 3 -> [[1,2,3],[4,5,6]]

split :: [a] -> Int -> [[a]]
split as n
  | length as < n = []
  | otherwise = take n as : split (drop n as) n
-- | otherwise = [take n as] ++ (split (drop n as) n)

main :: IO ()
main = do
  print (split [1 .. 7] 3)