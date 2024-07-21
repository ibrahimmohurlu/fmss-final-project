"use server"

import { cookies } from "next/headers"

export default async function getAuthSession(): Promise<AuthSession> {
    const authHeader = cookies().get("auth")?.value;
    if (authHeader) {
        const base64Credentials = authHeader.split(' ')[1];
        const decodedCredentials = atob(base64Credentials);

        // Kullanıcı adı ve şifreyi ayır
        const [email, password] = decodedCredentials.split(':');
        return { authenticated: true, user: { email } }
    }
    return { authenticated: false, user: undefined }
}