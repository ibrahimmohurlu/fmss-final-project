import NextAuth, { type DefaultSession } from "next-auth"
import Credentials from "next-auth/providers/credentials"
import fetchUser from "./utils/fetchUser"

declare module "next-auth" {
    /**
     * The shape of the user object returned in the OAuth providers' `profile` callback,
     * or the second parameter of the `session` callback, when using a database.
     */
    interface User {
        code: string

    }
    /**
     * The shape of the account object returned in the OAuth providers' `account` callback,
     * Usually contains information about the provider being used, like OAuth tokens (`access_token`, etc).
     */
    interface Account { }

    /**
     * Returned by `useSession`, `auth`, contains information about the active session.
     */
    interface Session {
        user?: {
            code: string
        } & DefaultSession["user"]
    }
}

export const { handlers, signIn, signOut, auth } = NextAuth({
    callbacks: {
        async jwt({ token, user }) {

            if (user) {
                token.id = user.id;
                token.email = user.email;
                token.code = user.code;
            }
            return token;
        },
        async session({ session, token }) {
            if (token) {
                session.user.id = token.id as string;
                session.user.email = token.email as string;
                session.user.name = token.name;
                session.user.code = token.code as string;
            }

            return session;
        }
    },
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
                const code = new Buffer(`${email}:${password}`, "base64").toString();
                // return user object with the their profile data
                return { ...user, code }
            },
        }),
    ],
})