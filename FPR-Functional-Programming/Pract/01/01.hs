module Main where

main :: IO ()
main = do
  print (isWithinCircularBand (1, 1) 0 2)
  print (isWithinCircularBand (1, 1) 2 3)

-- isWithinCircularBand :: (Double, Double) -> Double -> Double -> Bool
isWithinCircularBand (x, y) r1 r2 =
  let dist = sqrt (x ^ 2 + y ^ 2)
   in dist >= r1 && dist <= r2