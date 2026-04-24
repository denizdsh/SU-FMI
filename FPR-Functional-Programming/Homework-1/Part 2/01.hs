-- import Debug.Trace (trace)

data Laptop = Laptop
  { brand :: String,
    year :: Int,
    ramGB :: Int,
    price :: Double
  }

-- Helper functions
average :: (Fractional a) => [a] -> a
average [] = 0
average xs = sum xs / fromIntegral (length xs)

-- Task logic
createAvgPriceFunc :: [Laptop] -> (String, Int) -> Double
createAvgPriceFunc [] _ = 0.0
createAvgPriceFunc laptops (b, y) =
  average [price l | l <- laptops, brand l == b && year l > y] -- or year l >= y ?

avgPriceFunc :: (String, Int) -> Double
avgPriceFunc = createAvgPriceFunc laptops

getTotalPrice :: [Laptop] -> [Int] -> [(String, Double)] -> Double
getTotalPrice [] _ _ = 0.0
getTotalPrice _ [] _ = 0.0
getTotalPrice allLaptops laptopCoefs expenses =
  sum $
    map (\(l, (coef, exp)) -> fromIntegral coef * ((1 + exp) * price l)) $
      zipWith (\l coef -> (l, (coef, getExpense l))) (take len allLaptops) (take len laptopCoefs)
  where
    len = min (length laptops) (length laptopCoefs)
    getExpense l =
      snd $
        head $
          foldr (\(b, expense) acc -> if b == brand l then (b, expense) : acc else acc) [("default", 0)] expenses

-- findMaxParam :: (Ord a) => (Laptop -> a) -> [Laptop] -> Laptop
-- findMaxParam _ [] = error "Empty laptop list"
-- findMaxParam param laptops = foldr1 (\l1 l2 -> if param l1 > param l2 then l1 else l2) laptops

laptopParamSumByYear :: (Num a) => (Laptop -> a) -> [Laptop] -> [(Int, a)]
laptopParamSumByYear _ [] = error "Empty laptop list"
laptopParamSumByYear param laptops =
  [ (uniqueYear, sum $ map param $ filter (\l -> year l == uniqueYear) laptops)
    | uniqueYear <- foldr (\l acc -> if year l `elem` acc then acc else year l : acc) [] laptops
  ]

maxLaptopParamSumByYear :: (Num a, Ord a) => (Laptop -> a) -> [Laptop] -> Int
maxLaptopParamSumByYear param laptops =
  fst $
    foldr1
      (\(y, ps) (maxYear, maxSum) -> if ps > maxSum then (y, ps) else (maxYear, maxSum))
      (laptopParamSumByYear param laptops)

findYear :: [Laptop] -> Int
findYear = maxLaptopParamSumByYear ramGB

laptops :: [Laptop]
laptops =
  [ Laptop {brand = "Dell", year = 2020, ramGB = 16, price = 1500.0},
    Laptop {brand = "Apple", year = 2021, ramGB = 8, price = 2000.0},
    Laptop {brand = "Lenovo", year = 2020, ramGB = 32, price = 1800.0},
    Laptop {brand = "Asus", year = 2019, ramGB = 16, price = 1200.0},
    Laptop {brand = "Dell", year = 2021, ramGB = 8, price = 1000.0}
  ]

main :: IO ()
main = do
  print $ avgPriceFunc ("Dell", 2019) -- Очакван резултат: 1250.0
  print $
    getTotalPrice -- Очакван резултат: 24480.0 (22280 в условие, но ръчно изчислено 24480)
      laptops
      [1, 2, 3, 4, 5]
      [("Apple", 0.5), ("Asus", 0.1), ("Dell", 0.2)]
  print $ findYear laptops -- Очакван резултат: 2020
