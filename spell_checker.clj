(defn map-count [M K] 
  (assoc M K (inc (get M K 0))))

(def NWORDS
	(reduce map-count {} (re-seq #"[a-z]+" (clojure.string/lower-case (slurp "/Users/jayanth/Desktop/big.txt")))))

(def alphabets ["a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m" "n" "o" "p" "q" "r" "s" "t" "u" "v" "w" "x" "y" "z" ""])

(defn edits1 [word]
	(def add1 [])
	(def add1 (conj add1 (map #(str word %) alphabets)))
	(def add1 (conj add1 (map #(str % word) alphabets)))
	(dotimes [i (.length word)]
	(def add1 (conj add1 (map #(str (subs word 0 i) % (subs word i (.length word))) alphabets))))
	(flatten add1))

(defn edits2 [word]
	(def e1 (edits1 word))
	(flatten (map edits1 e1)))

(defn word? [word]
	(> (get NWORDS word 0) 0))
	
(defn known-words [words]
	
	(filter word? words))
(defn compare-words [a b]
	(cond 
		(> (get NWORDS a 0) (get NWORDS b 0)) a
		:else b
		))
(defn correct [word]
	(cond 
		(word? word) (println "correct")
		(> (count (known-words (edits1 word))) 1) (println (reduce compare-words (known-words (edits1 word))))
		(= (count (known-words (edits1 word))) 1) (println (first (known-words (edits1 word))) )
		(> (count (known-words (edits2 word))) 1) (println (reduce compare-words (known-words (edits2 word))))
		(= (count (known-words (edits2 word))) 1) (println (first (known-words (edits2 word))) )
		:else (println "unknown word")
		))


