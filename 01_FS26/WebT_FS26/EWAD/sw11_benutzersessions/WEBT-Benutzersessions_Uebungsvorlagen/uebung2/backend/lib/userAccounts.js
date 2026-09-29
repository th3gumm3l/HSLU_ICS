// Example for implementing user accounts in Node.js using bcryptjs.
// This example, while reasonably secure, is not suitable for production usage.
import bcrypt from 'bcryptjs';

const costs = 10;

/**
 * Creates an instance for registering and authenticating user accounts.
 * @param {Collection} collection - Mongo DB collection to store/fetch user accounts.
 * @returns instance for registering / authenticating users
 */
async function createUserAccounts(collection) {
    const auth = {
        collection: collection,
        register: async function(username, password) {
            try {
                await this.collection.insertOne({
                    username       : username,
                    hashedPassword : await bcrypt.hash(password, costs)
                });
            } catch (error) {
                // rethrow all but duplicate key errors (== user already registered)
                if (error.code != 11000 /* DUPLICATE KEY */) throw error;
                return false;
            }
            return true;
        },
        authenticate: async function(username, password) {
            const userAccount = await this.collection.findOne({ username: { $eq : username }});
            return userAccount != null && await bcrypt.compare(password, userAccount.hashedPassword);
        }
    };
    await collection.createIndex({"username": 1}, { unique: true });
    return auth;
}

export default createUserAccounts;