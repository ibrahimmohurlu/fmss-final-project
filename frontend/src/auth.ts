import NextAuth from "next-auth"
import Credentials from "next-auth/providers/credentials"
import fetchUser from "./utils/fetchUser"
export const { handlers, signIn, signOut, auth } = NextAuth({
    providers: [
        Credentials({
            // You can specify which fields should be submitted, by adding keys to the `credentials` object.
            // e.g. domain, username, password, 2FA token, etc.
            credentials: {
                email: {},
                password: {},
            },
            authorize: async (credentials) => {
                let user = null

                const email = credentials.email as string;
                const password = credentials.password as string;

                // logic to verify if user exists
                user = await fetchUser(email, password)

                if (!user) {
                    // No user found, so this is their first attempt to login
                    // meaning this is also the place you could do registration
                    throw new Error("User not found.")
                }

                // return user object with the their profile data
                return user
            },
        }),
    ],
})