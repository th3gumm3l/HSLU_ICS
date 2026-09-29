namespace HashingAlgorithm
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] passwords = { "Haus", "Maus", "Raus", "Laus", "aLus" };
            string[] salts = { "x9Kp", "Qm3z", "7wLe", "Bv0j", "nR5d" };

            Console.WriteLine("=== Ohne Salt ===");
            foreach (var pw in passwords)
            {
                string hash = HashingAlgorithm(pw);
                Console.WriteLine($"Password: {pw} -> Hash: {hash}");
            }

            Console.WriteLine();
            Console.WriteLine("=== Mit Salt ===");
            for (int i = 0; i < passwords.Length; i++)
            {
                string salted = passwords[i] + salts[i];
                string hash = HashingAlgorithm(salted);
                Console.WriteLine($"Password: {passwords[i]} + Salt: {salts[i]} -> Salted Input: {salted} -> Hash: {hash}");
            }
        }

        static string HashingAlgorithm(string input)
        {
            int[] positionPrimes = { 7, 31, 127, 211, 53, 97, 151, 241 };
            long hash = 0;
            for (int i = 0; i < input.Length; i++)
            {
                int charValue = input[i];
                int prime = positionPrimes[i % positionPrimes.Length];
                hash += charValue * prime * (i + 1);
                hash *= 31;
                hash += charValue * charValue + i * 997;
            }
            hash = Math.Abs(hash);
            hash = hash % 90000000 + 10000000;
            return hash.ToString("D8");
        }
    }
}