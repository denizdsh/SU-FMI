findCommonCount :: (Eq a) => [a] -> [a] -> Int
findCommonCount xs ys = length [x | x <- xs, y <- ys, x == y]

-- main :: IO ()
-- main =
  -- print $ findCommonCount [1, 2, 3, 4] [1, 3, 2, 5, 4]