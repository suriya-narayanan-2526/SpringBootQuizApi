package finalDemoApplication.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import finalDemoApplication.demo.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private Jwtutil jwt;
	@Autowired
	private CustomUserDetailsService custom;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authHeader = request.getHeader("Authorization");
		System.out.println(authHeader);
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
             
			filterChain.doFilter(request, response);
			return;
		}
		String token = authHeader.substring(7).trim();
		try
		{
			System.out.println("in try");
			String username = jwt.extractUserName(token);
			UserDetails user = custom.loadUserByUsername(username);
			System.out.println("Extracted Username: " + username);
			System.out.println("Token Valid: " + jwt.validateToken(token, user));
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				if(jwt.validateToken(token,user))
				{
					UsernamePasswordAuthenticationToken authToken = new	UsernamePasswordAuthenticationToken
							(user.getUsername(),user.getPassword(),user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		catch(Exception e)
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		filterChain.doFilter(request, response);

	}

}
