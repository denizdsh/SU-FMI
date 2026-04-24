windows :: [a] -> Int -> [[a]]
windows xs k
  | k > length xs = []
  -- error "length (k) is greater than the length of the list"
  | k <= 0 = []
  -- error "length (k) must be greater than 0"
  | otherwise =
      map (\(i, _) -> take k $ drop i xs) -- fill subarray with k elements from xs starting from index
        . zip [0 ..] -- pair with index
        $ take (length xs - k + 1) xs -- remove last (< k) elements

minWindowProduct :: (Num a, Ord a) => [a] -> Int -> a
minWindowProduct lst k
  | k > length lst = error "length (k) is greater than the length of the list"
  | k <= 0 = error "length (k) must be greater than 0"
  | otherwise = minimum . map product $ windows lst k

main :: IO ()
main = do
  print $ minWindowProduct [1, 2, 3, 4, 5, 0] 3 -- 0
  print $ minWindowProduct [1, -2, 3, -4, 5, 0] 2 -- -20