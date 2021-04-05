(ns fourclojure.core)

(defn last-element
  "Return the last element in a sequence without using last."
  [s]
  (if (empty? (rest s))
    (first s)
    (last-element (rest s))))

(defn penultimate-element
  "Return the penultimate element in a sequence without using last."
  [s]
  (if (= 1 (count (rest s)))
    (first s)
    (penultimate-element (rest s))))

(defn penultimate
  "Return the penultimate element of a sequence"
  [s]
  ((comp second reverse) s))

(defn nth-element
  "Return the nth element in a sequence without using nth."
  [s n]
  (if (pos? n)
    (nth-element (rest s) (dec n))
    (first s)))

(defn count-a-sequence
  "Return the count of items in a sequence without using count"
  [s]
  (loop [n 0]
    (if-not (nth s n false)
      n
      (recur (+ n 1)))))

(defn reverse-a-sequence
  "Reverse a sequence without using reverse"
  [s]
  (loop [s s
         r '()]
    (if-not (first s)
      r
      (recur (rest s) (conj r (first s))))))

(defn sum
  "Returns the sum of a sequence of numbers"
  [s]
  (reduce + s))

(defn odd-numbers
  "Returns only the odd numbers from a sequence"
  [s]
  (filter odd? s))

(defn fibonacci-sequence
  "Returns the first X fibonacci numbers"
  [n]
  (loop [fs '(1 1)]
    (if (= (count fs) n)
      (reverse fs)
      (recur (conj fs (+ (first fs) (second fs)))))))

(defn palindrome-detector
  "Returns true if sequence is a palindrome"
  [s]
  (loop [i 0]
    (if (= (nth s i) (nth s (- (count s) (+ i 1))))
      (if (>= i (- (count s) i))
        true
        (recur (inc i)))
      false)))

(defn flatten-sequence
  "Flattens a sequence"
  [s]
  (loop [s s
         f '()]
    (if (= 0 (count s))
      (reverse f)
      (if (instance? clojure.lang.Seqable (first s))
        (recur (concat (first s) (rest s)) f)
        (recur (rest s) (conj f (first s)))))))

(defn caps
  "Returns only capital letters"
  [s]
  (apply str (re-seq #"[A-Z]" s)))

(defn compress
  "Removes consecutive duplicates from a sequence"
  [s]
  (loop [n 1
         ns (list (first s))]
    (if (= (count s) n)
      (reverse ns)
      (if (= (nth s n) (nth s (dec n)))
        (recur (inc n) ns)
        (recur (inc n) (conj ns (nth s n)))))))

(defn pack-a-sequence
  "Packs consecutive duplicates into sub-lists"
  [s]
  (loop [n 1
         ns (list (list (first s)))]
    (if (= (count s) n)
      (reverse ns)
      (if (= (nth s n) (nth s (dec n)))
        (recur (inc n) (conj (rest ns) (conj (first ns) (nth s n))))
        (recur (inc n) (conj ns (list (nth s n))))))))

(defn duplicate-a-sequence
  "Duplicates each element of a sequence"
  [s]
  (interleave s s))

(defn replicate-a-sequence
  "Replicates each element of a sequence a variable number of times"
  [s n]
  (apply concat (map #(repeat n %) s)))

(defn implement-range
  "Creates a list of all integers in a given range without range"
  [i j]
  (loop [r (list i)]
    (if (= (count r) (- j i))
      (reverse r)
      (recur (conj r (inc (first r)))))))

(defn maximum-value
  "Returns the maximum value without using max"
  [& args]
  (reduce #(if (>= %1 %2) %1 %2) args))

(defn interleave-two-seqs
  "Interleave without iterleave"
  [s1 s2]
  (flatten (map #(list %1 %2) s1 s2)))

(defn interpose-a-seq
  "Separates the items of a sequence by an arbitrary value"
  [v s]
  (butlast (flatten (map #(list %1 %2) s (repeat v)))))

(defn drop-every-nth-item
  "Drops every Nth item from a sequence"
  [s n]
  (keep-indexed #(if (= 0 (rem (inc %1) n)) nil %2) s))

(defn factorial-fun
  "Calculates factorials"
  [n]
  (reduce * (range 1 (inc n))))

(defn reverse-interleave
  "Reverses the interleave process into x number of subsequences"
  [s n]
  (apply map list (partition n s)))

(defn rotate-sequence
  "Rotates a sequence"
  [n s]
  (loop [s s
         i 0]
    (if (= i (- (count s) (mod n (count s))))
      s
      (recur (conj (butlast s) (last s)) (inc i)))))

(defn flipping-out
  "Higher-order function which flips the order of the arguments of an input function"
  [f]
  (fn [& args] (apply f (reverse args))))

(defn split-sequence
  "Splits a sequence into two parts"
  [n s]
  (loop [fs []
         ss []
         i 0]
    (if (= i (count s))
      [fs ss]
      (if (> n i)
        (recur (conj fs (nth s i)) ss (inc i))
        (recur fs (conj ss (nth s i)) (inc i))))))

(defn split-by-type
  "Takes a sequence consisting of items with different types and splits them up into a set of homogeneous sub-sequences"
  [s]
  (vals (group-by #(type %) s)))

;; The longest-increasing-sub-seq function caused me a lot of trouble
;; so any time I felt like I needed to make changes that were a major
;; branch from what my previous attempt was, I just made a new func

;; (defn longest-increasing-sub-seq1
;;   "Find the longest consecutive sub-sequence of increasing numbers"
;;   [s]
;;   (filter [max-len-ss] (if (>= (count max-len-ss) 2) max-len-ss [])
;;           (reduce #(if (>= (count %1) (count %2)) %1 %2)
;;                   (reduce
;;                    (fn [rs el]
;;                      (if rs
;;                        (if (= (inc (last (last rs))) el)
;;                          (conj (vec (butlast rs)) (conj (last rs) el))
;;                          (conj rs [el]))
;;                        [[el]]))
;;                    nil s))))

;; (defn longest-increasing-sub-seq2
;;   "Find the longest consecutive sub-sequence of increasing numbers"
;;   [s]
;;   (if-let [max-len-ss
;;            (reduce #(if (>= (count %1) (count %2)) %1 %2)
;;                    (reduce
;;                     (fn [rs el]
;;                       (if rs
;;                         (if (= (inc (last (last rs))) el)
;;                           (conj (vec (butlast rs)) (conj (last rs) el))
;;                           (conj rs [el]))
;;                         [[el]]))
;;                     nil s))]
;;     (if-let [ret-val (if (>= (count max-len-ss) 2) max-len-ss [])] ret-val []) []))

;; (defn longest-increasing-sub-seq3
;;   "Find the longest consecutive sub-sequence of increasing numbers"
;;   [s]
;;   (or (some #(if (>= (count (vec %)) 2) % nil)
;;             (reduce #(if (>= (count %1) (count %2)) %1 %2)
;;                     (reduce
;;                      (fn [rs el]
;;                        (if rs
;;                          (if (= (inc (last (last rs))) el)
;;                            (conj (vec (butlast rs)) (conj (last rs) el))
;;                            (conj rs [el]))
;;                          [[el]]))
;;                      nil s))) []))

;; (defn longest-increasing-sub-seq4
;;   "Find the longest consecutive sub-sequence of increasing numbers"
;;   [s]
;;   (apply #(if (>= (count %) 2) % nil)
;;          (reduce #(if (>= (count %1) (count %2)) %1 %2)
;;                  (vec (reduce
;;                        (fn [rs el]
;;                          (if rs
;;                            (if (= (inc (last (last rs))) el)
;;                              (conj (vec (butlast rs)) (conj (last rs) el))
;;                              (conj rs [el]))
;;                            [[el]]))
;;                        nil s)))))

(defn longest-increasing-sub-seq
  "Find the longest consecutive sub-sequence of increasing numbers"
  [s]
  (#(if (>= (count %) 2) % [])
   (reduce #(if (>= (count %1) (count %2)) %1 %2)
           (vec (reduce
                 (fn [rs el]
                   (if rs
                     (if (= (inc (last (last rs))) el)
                       (conj (vec (butlast rs)) (conj (last rs) el))
                       (conj rs [el]))
                     [[el]]))
                 nil s)))))

(defn partition-a-sequence
  "Returns a sequence of lists of x items each without partition"
  [n s]
  (letfn [(partition-reduce [n s rs] (if (>= (count s) n) (partition-reduce n (drop n s) (conj rs (take n s))) rs))]
    (partition-reduce n s [])))

(defn count-occurrences
  "Returns a map containing the number of occurences of each distinct item in a sequence"
  [s]
  (into (sorted-map) (map (fn [%] [(first %) (count %)]) (partition-by identity (sort s)))))

(defn find-distinct-items
  "Returns a map containing the number of occurences of each distinct item in a sequence"
  [s]
  (reduce #(if (some #{%2} %1) %1 (conj %1 %2)) [] s))

(defn function-composition
  "Takes a variable number of functions, and creates a function that applies them from right-to-left"
  [& fns]
  (fn [& args] (first (reduce #(list (apply %2 %1)) args (reverse fns)))))

(defn juxtaposition
  "Take a set of functions and return a new function that takes arguments and returns a sequence containing the result of applying each function left-to-right to the argument list"
  [& fns]
  (fn [& args] (reduce #(conj %1 (apply %2 args)) [] fns)))

(letfn [(intermediate-reduce [f coll redseq]
          (lazy-seq (if (first coll)
                      (intermediate-reduce f (rest coll) (conj redseq (f (last redseq) (first coll))))
                      redseq)))]
  (defn sequence-reductions1
    "Behaves like reduce, but returns each intermediate value of the reduction"
    ([f val coll]
     (intermediate-reduce f coll [val]))
    ([f coll]
     (intermediate-reduce f (drop 2 coll) [(f (first coll) (second coll))]))))

(defn sequence-reductions2
  "Behaves like reduce, but returns each intermediate value of the reduction"
  ([f val coll]
   (let [redseq [val]]
     (map #(last (conj redseq (f (last redseq) %1))) coll))))

(defn sequence-reductions3
  "Behaves like reduce, but returns each intermediate value of the reduction"
  ([f val coll]
   (lazy-seq (iterate (fn [redseq] (conj redseq (f (last redseq) (first coll)))) val))))

(defn sequence-reductions4
  "Behaves like reduce, but returns each intermediate value of the reduction"
  ([f val coll]
   (reduce (fn [redseq val]
             (conj redseq (f (last redseq) val)))
           [val]
           coll))
  ([f coll]
   (reduce (fn [redseq val]
             (conj redseq (f (last redseq) val)))
           [(f (first coll) (second coll))]
           (drop 2 coll))))

(defn sequence-reductions
  "Behaves like reduce, but returns each intermediate value of the reduction"
  ([f val coll]
   (if (list? val)
     (list @val)
     (cons val
           (lazy-seq
            (when-let [s (seq coll)]
              (sequence-reductions f (f val (first s)) (rest s)))))))
  ([f coll]
   (when-let [s (seq coll)]
     (sequence-reductions f (first s) (rest s)))))

(defn map-construction
  "takes a vector of keys and a vector of values and constructs a map from them"
  [keys vals]
  (into (hash-map) (map (fn [k v] [k v]) keys vals)))

(defn my-iterate
  "takes a function and a value and returns an infinite sequence of (f x) (f (f x)) etc"
  [f x]
  (cons x
        (lazy-seq
         (my-iterate f (f x)))))

(defn group-a-sequence1
  "Returns a map. Keys are (apply f s). Values are corresponding items in the order they appear in s"
  [f s]
  (apply merge-with #(if (coll? (first %1))
                       (conj %1 %2)
                       [%1 %2])
         (map #(hash-map (f %) %) s)))

(defn group-a-sequence2
  "Returns a map. Keys are (apply f s). Values are corresponding items in the order they appear in s"
  [f s]
  ((fn build-map
     [map kvs]
     (when-let [s (seq kvs)]
       (let [k (get (first s) 0) v (get (first s) 1)]
         (build-map (if (get map k)
                      (assoc map k (conj (get map k) v))
                      (assoc map k [v]))
                    (rest s)))))
   {}
   (map #(vector (f %) %) s)))

(defn group-a-sequence
  "Returns a map. Keys are (apply f s). Values are corresponding items in the order they appear in s"
  [f s]
  ((fn build-map
     [map kvs]
     (if-let [s (seq kvs)]
       (build-map (if (get map (get (first s) 0))
                    (assoc map (get (first s) 0) (conj (get map (get (first s) 0)) (get (first s) 1)))
                    (assoc map (get (first s) 0) [(get (first s) 1)]))
                  (rest s))
       map))
   {}
   (map #(vector (f %) %) s)))

(defn gcd
  "Given two integers, returns the greatest common divisor"
  [x y]
  (letfn [(divisors [n] (filter #(zero? (rem n %)) (range 1 (inc n))))]
    (apply max (filter (set (divisors y)) (divisors x)))))