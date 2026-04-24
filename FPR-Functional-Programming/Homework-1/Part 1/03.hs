pascalStep :: [Integer] -> [Integer]
-- [1,2,1] -> [1,2] + [2,1] -> 1 : [3,3] : 1
-- pascalStep row = 1 : zipWith (+) (tail row) (init row) ++ [1]

--  [1,2,1] -> [0,1,2,1] + [1,2,1,0] -> [1,3,3,1]
pascalStep row = zipWith (+) (0 : row) (row ++ [0]) -- unnoticably better performance :)

pascalTriangle :: [[Integer]]
pascalTriangle = iterate pascalStep [1]

-- [1]
-- [1,1]
-- [1,2,1]
-- [1, 3, 3, 1]
-- [1, 4, 6, 4, 1]
-- [1, 5, 10, 10, 5, 1]

main :: IO ()
main = do
  print $ take 6 pascalTriangle