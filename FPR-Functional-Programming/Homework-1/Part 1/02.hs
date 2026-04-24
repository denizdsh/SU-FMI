import Data.List (find)

-- Could rename "mapOnce" to "findAndMap",
-- remove use of "find" and make "findAndMap" work with Monads instead

mapOnce :: (a -> Bool) -> (a -> a) -> [a] -> [a]
mapOnce _ _ [] = []
mapOnce pred func (x : xs)
  | pred x = func x : xs
  | otherwise = x : mapOnce pred func xs

addToSplit :: (Eq a) => [[a]] -> a -> [[a]]
addToSplit split x =
  case sub of
    Nothing -> split ++ [[x]] -- x found in every sublist, create a new one
    Just sub -> mapOnce (== sub) (x :) split -- add x to first available sublist
  where
    sub = find (\sub -> x `notElem` sub) split

minimalSplitWith :: (Eq a) => [a] -> [[a]] -> [[a]]
minimalSplitWith [] split = split
minimalSplitWith (x : xs) split = minimalSplitWith xs $ addToSplit split x

minimalSplit :: (Eq a) => [a] -> [[a]]
minimalSplit [] = []
minimalSplit lst = lst `minimalSplitWith` [[]]

main :: IO ()
main = do
  print $ minimalSplit [1, 2, 3, 4, 2, 3, 2, 1] -- Примерен изход: [[1,2,3,4],[2,3],[2,1]]
  print $ minimalSplit [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 11, 12, 10, 10, 9, 10, 7, 8, 6, 5, 4, 32, 3, 1, 2, 9, 12]