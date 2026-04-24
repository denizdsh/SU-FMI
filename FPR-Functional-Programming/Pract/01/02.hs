module Main where

main :: IO ()
main = do
  print (getPrevSmallerDigit 1239)

isIncreasingDigits :: Int -> Bool
isIncreasingDigits n = getPrevSmallerDigit n > -1

getPrevSmallerDigit :: Int -> Int
getPrevSmallerDigit n =
  if m < n `mod` 10 then m else -1
  where
    m = getPrevSmallerDigit n `div` 10


-- 123456789 | 9 < 8 < 7 < 6 < 5 < 4 < 3 < 2 < 1 < 0 !< 0 -> -1