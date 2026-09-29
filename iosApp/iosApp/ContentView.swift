import SwiftUI
import Shared

struct ContentView: View {
    private let info = EcosystemInfo()

    var body: some View {
        VStack(spacing: 24) {
            VStack(spacing: 4) {
                Text("Mi Ecosystem")
                    .font(.largeTitle.weight(.bold))
                Text("Technical Bootstrap")
                    .font(.title3)
                    .foregroundStyle(.secondary)
            }

            VStack(spacing: 8) {
                Text("Shared core: OK")
                    .font(.body)
                Text(info.platformMessage())
                    .font(.caption)
                    .foregroundStyle(.secondary)

                Text("Platform: iOS")
                    .font(.body)
            }
        }
        .multilineTextAlignment(.center)
        .padding()
    }
}
