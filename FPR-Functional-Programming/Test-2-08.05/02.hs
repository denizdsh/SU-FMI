data Person = Person {name :: String, birthyear :: Int, height :: Int}

findAverageHeight :: [Person] -> Int
findAverageHeight xs = sum hs `div` length hs
  where
    hs = map height xs

tallPeopleNames :: [Person] -> [String]
tallPeopleNames xs = [name x | x <- xs, height x > findAverageHeight xs]

hasRepeated :: (Eq a) => [a] -> Bool
hasRepeated (x : xs) = (x `elem` xs) || hasRepeated xs
hasRepeated [] = False

fertileYear :: [Person] -> Bool
fertileYear xs = hasRepeated $ map birthyear xs

-- people =
--   [ Person {name = "Ivan", birthyear = 1999, height = 185},
--     Person {name = "Petar", birthyear = 2002, height = 179},
--     Person {name = "Maria", birthyear = 2003, height = 182},
--     Person {name = "Maria", birthyear = 2003, height = 182}
--   ]

-- main :: IO ()
-- main =
--   print $ fertileYear people
